package clientdomain

import jzdomain.RfidRegistration
import jzdomain.RfidCard
import clientdomain.RfidReader

class SimpleRfidRegistrationController {

  //static allowedMethods = [save:'POST'] //Restricts 'get' and similar to save! Should be post.

  def save = {
    RfidReader registeredByApplication
    RfidCard foundRfidCard
    Date timedAtTimeStamp

    if (params.registeredBy)
      registeredByApplication = RfidReader.findByReaderId(params.registeredBy)
    if (params.rfidId)
      foundRfidCard = RfidCard.findByRfidId(params.rfidId)
    if (params.timestamp)
      timedAtTimeStamp = new Date().parse("d.M.yyyy-H:m:s", params.timestamp);

    if (registeredByApplication && foundRfidCard) {
      if (!timedAtTimeStamp)
        timedAtTimeStamp = new Date() //Create a new timestamp in case one was not given.

      def newRegistration = new RfidRegistration(rfidCard: foundRfidCard, registeredBy: registeredByApplication, timestamp: timedAtTimeStamp)
      newRegistration.save()
      redirect(controller: 'person', action: "show", params: [id: foundRfidCard.ownedBy.id, format:'xml'])
    }
    //Error handling
    else {
      if(!registeredByApplication && params.registeredBy)
        render("No RFID Reader application in the DB with ID $params.registeredBy")
      else if(!foundRfidCard && params.rfidId)
        render("No RFID with the ID $params.rfidId")
      else {
        def helpurl = "http://localhost:8080/rfid-server/html/simpleRfidRegistration/save?rfidId=135aa1fd&registeredBy=Gunnars%20orange%20registreringsleser&timestamp=3.10.2009-09:30:13"
        render("Correct usage of service: <a href='$helpurl'>$helpurl</a>")
      }
    }
  }
}