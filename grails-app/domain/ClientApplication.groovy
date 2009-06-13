import clientdomain.Location

class ClientApplication {
	List<RfidReader> rfidReaders //1..*
	List<Location> locations //*..1
	String about
	List<RfidRegistration> registrations //1..*
}