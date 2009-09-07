package clientdomain

import org.codehaus.groovy.grails.plugins.springsecurity.Secured

@Secured(['ROLE_USER'])
class CompanyUserController {
  def scaffold = CompanyUser
}
