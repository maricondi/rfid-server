package clientdomain

import jzdomain.RfidRegistration

class ClientApplication {
	List<RfidReader> rfidReaders //1..*
	String about
	//List<RfidRegistration> registrations //1..*
}