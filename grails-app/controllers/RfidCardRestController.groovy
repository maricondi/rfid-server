import grails.converters.*

class RfidCardRestController {

  def index = { redirect(action:list,params:params) }

  def list = {
     withFormat {
      xml { render RfidCard.list() as XML }
      json { render RfidCard.list() as JSON }
    }
  }

  def show = {
    RfidCard rfidcardInstance = RfidCard.get(params.id)
    if (rfidcardInstance) {
      render rfidcardInstance as XML
    } else {
      sendNotFoundResponse("RfidCard not found with id: $params.id")
    }
  }

  def create = {
    def rfidcardInstance = new RfidCard(params['rfidcardInstance'])
    if(rfidcardInstance.validate() && !rfidcardInstance.hasErrors() && rfidcardInstance.save()) {
      response.status = 201
      render rfidcardInstance as XML
    } else {
      sendValidationFailedResponse(rfidcardInstance, 403)
    }
  }

  def update = {
    RfidCard rfidcardInstance = RfidCard.get(params.id)
    if (!rfidcardInstance) {
      sendNotFoundResponse("RfidCard not found with id: $params.id")
    }else {
      rfidcardInstance.properties = params

      if (rfidcardInstance.validate() && rfidcardInstance.save()) {
        response.status = 201
        render rfidcardInstance as XML
      } else {
        sendValidationFailedResponse(rfidcardInstance, 403)
      }
    }
  }

  def delete = {
    RfidCard rfidcardInstance = RfidCard.get(params.id)

    if (rfidcardInstance) {
      try {
        rfidcardInstance.delete(flush: true)
        response.status = 204
        render ""
      }catch (Exception e) { }
    }
    sendNotFoundResponse("Could not delete rfidcard with id: $params.id")
  }

  private def sendValidationFailedResponse(rfidcard, status) {
    response.status = status
    render contentType: "application/xml", {
      errors {
        rfidcard?.errors?.fieldErrors?.each {err ->
          field(err.field)
          message(g.message(error: err))
        }
      }
    }
  }

  private def sendNotFoundResponse(String myMessage) {
    response.status = 404
    render contentType: "application/xml", {
      errors {
        message(myMessage)
      }
    }
  }
}
