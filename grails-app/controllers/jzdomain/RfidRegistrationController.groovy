package jzdomain

import org.codehaus.groovy.grails.plugins.springsecurity.Secured

@Secured(['ROLE_USER'])
class RfidRegistrationController {
  def scaffold = RfidRegistration
}
