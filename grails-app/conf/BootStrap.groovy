import clientdomain.*
import jzdomain.*

class BootStrap {

  def init = {servletContext ->
    //Create a test dummy set of data
    Location locationForRfidReader1 = new Location(xyLocation:"Here", cleartext:"Inngang sal 5").save()
    RfidReader rfidReader1 = new RfidReader(readerId:"Gunnars blå registreringsleser", readerLocation:locationForRfidReader1).save()
    new ClientApplication(about:"CompanyXXx's SuperDuper registration app", rfidReaders:[rfidReader1]).save()

    Person person1 = new Person(fullName: "Roger Moore", email: "roger@moore.com", company: "roger more", isHero: false, alumni: false).save()
    RfidCard rfidcard1 = new RfidCard(rfidId:"id one", ownedBy:person1).save()

    new RfidRegistration(rfidCard:rfidcard1, registeredBy:rfidReader1, timestamp:new Date()).save()
  }
  def destroy = {
  }
}