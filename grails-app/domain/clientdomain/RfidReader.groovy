package clientdomain
class RfidReader {
	String readerId
    Location readerLocation
    ClientApplication usedByClientApplication
    String toString() {"RfidReader: $readerId"}
}