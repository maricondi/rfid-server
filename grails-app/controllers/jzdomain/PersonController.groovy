package jzdomain

import org.codehaus.groovy.grails.plugins.springsecurity.Secured

@Secured(['ROLE_USER'])
class PersonController {
  def scaffold = Person
}