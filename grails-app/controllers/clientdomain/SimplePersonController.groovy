package clientdomain

import jzdomain.Person

import grails.converters.XML
import grails.converters.JSON

class SimplePersonController {

//Use ?format=xml to handle other content types


  def index = { redirect(action:show,params:params) }

  // the delete, save and update actions only accept POST requests
  static allowedMethods = []


  def show = {
    def personInstance = Person.getByRfidId( params.id )
    if(!personInstance) {
          render( "Person not found with id ${params.id}")
    }
    else {
      withFormat {
        html { return [ personInstance : personInstance ] }
        xml { render personInstance as XML }
        json { render personInstance as JSON }
      }
    }
  }
}