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
      registeredByApplication = RfidReader.get(params.registeredBy)
    if (params.rfidId)
      foundRfidCard = RfidCard.get(params.rfidId)
    if (params.timestamp)
      timedAtTimeStamp = new Date().parse("d.M.yyyy-H:m:s", params.timestamp);

    if (registeredByApplication && foundRfidCard) {
      if(!timedAtTimeStamp)
        timedAtTimeStamp = new Date() //Create a new timestamp in case one was not given.

      def newRegistration = new RfidRegistration(rfidCard:foundRfidCard, registeredBy:registeredByApplication, timestamp:timedAtTimeStamp)
      newRegistration.save()
      redirect(controller:'person', action:"show", params:[id:foundRfidCard.ownedBy.id])
    }

    else
      render("Correct usage of service: http://localhost:8080/rfid-server/html/simpleRfidRegistration/save?rfidId=1&registeredBy=1&timestamp=3.10.2009-09:30:13")
  }
}