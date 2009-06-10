class CompanyUser {
	String id
	String password
	List<ClientApplication> clientApplications //1..*
}

class ClientApplication {
	List<RfidReader> rfidReaders //1..*
	List<Location> locations //*..1
	String about
	List<RfidRegistration> registrations //1..*
}

class RfidReader {
	String readerId
}

class RfidRegistration {
	RfidCard rfidCard
	Date timestamp
}

class Location {
	String xyLocation
	String cleartext
}
