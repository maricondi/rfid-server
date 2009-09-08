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
      redirect(controller: 'simplePerson', action: "show", params: [id: foundRfidCard.rfidId, format:'xml'])
    }
    //Error handling
    else {
	  if (!registeredByApplication && params.registeredBy && !foundRfidCard && params.rfidId)
        render("<xml><error name=\"UnknownRfidReader\">No RFID Reader application in the DB with ID $params.registeredBy</error><error name=\"UnknownRfid\">No RFID with the ID $params.rfidId</error></xml>")
      else if(!registeredByApplication && params.registeredBy)
        render("<xml><error name=\"UnknownRfidReader\">No RFID Reader application in the DB with ID $params.registeredBy</error></xml>")
      else if(!foundRfidCard && params.rfidId)
        render("<xml><error name=\"UnknownRfid\">No RFID with the ID $params.rfidId</error></xml>")
      else {
        def helpurl = "http://localhost:8080/rfid-server/html/simpleRfidRegistration/save?rfidId=135aa1fd&registeredBy=Gunnars%20orange%20registreringsleser&timestamp=3.10.2009-09:30:13"
        render("<xml><hint>Correct usage of service: <a href='$helpurl'>$helpurl</a></hint></xml>")
      }
    }
  }
}