package clientdomain

import jzdomain.RfidRegistration

class ClientApplication {
	String about
    static hasMany = [ registrations : RfidRegistration, rfidReaders:RfidReader ]
    String toString() {"Application: $about"}
}