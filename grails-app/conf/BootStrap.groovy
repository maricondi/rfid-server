import clientdomain.*
import jzdomain.*

class BootStrap {

  def init = {servletContext ->
    //Create a test dummy set of data

    //Insertion of RFID and person data by JavaZone
    Person person1 = new Person(fullName: "Roger Moore", email: "roger@moore.com", company: "roger more", isHero: false, alumni: false).save()
    Person person2 = new Person(fullName: "Daniel Craig", email: "daniel@imdb.com", company: "Daniels cakeshop", isHero: true, alumni: false).save()

    RfidCard rfidcard1 = new RfidCard(rfidId:"id one", ownedBy:person1).save()
    RfidCard rfidcard2 = new RfidCard(rfidId:"135aa1fd", ownedBy:person2).save()

    //Set up the RFID Reader
    Location locationForRfidReader1 = new Location(xyLocation:"Here", cleartext:"Inngang sal 5").save()
    ClientApplication myApplication = new ClientApplication(about:"CompanyXXx's SuperDuper registration app").save()
    RfidReader rfidReader1 = new RfidReader(readerId:"Gunnars blå registreringsleser", readerLocation:locationForRfidReader1, usedByClientApplication:myApplication).save()




    new RfidRegistration(rfidCard:rfidcard1, registeredBy:rfidReader1, timestamp:new Date()).save()
    new RfidRegistration(rfidCard:rfidcard2, registeredBy:rfidReader1, timestamp:new Date()).save()

  }
  def destroy = {
  }
}