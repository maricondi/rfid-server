package clientdomain

import org.codehaus.groovy.grails.plugins.springsecurity.Secured

@Secured(['ROLE_USER'])
class RfidReaderController {
  def scaffold = RfidReader
}
