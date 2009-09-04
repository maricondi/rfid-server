import clientdomain.*
import jzdomain.*

//import org.grails.plugins.springsecurity.service.AuthenticateService 

class BootStrap {
  def authenticateService

  def init = {servletContext ->
	//Create the security roles
	def adminRole = new Role(authority: "ROLE_ADMIN", description: "Administrator access").save()
	def userRole = new Role(authority: "ROLE_USER", description: "Ordinary authenticated user").save()
	def password = authenticateService.passwordEncoder("changeMe")
	
	def adminUser = new User(username:"admin",userRealName:"Administrator",passwd:password,enabled:true,emailShow:true,description:"admin account",email:"nomail@nomail.no").save()
	adminRole.addToPeople(adminUser)
	adminRole.save()
	
    //Create a test dummy set of data

    //Insertion of RFID and person data by JavaZone
    Person person1 = new Person(fullName: "Roger Moore", email: "roger@moore.com", company: "roger more", isHero: false, alumni: false, telephone:"+47 22 22 22 22").save()
    Person person2 = new Person(fullName: "Daniel Craig", email: "daniel@imdb.com", company: "Daniels cakeshop", isHero: true, alumni: false, telephone:"").save()

    RfidCard rfidcard1 = new RfidCard(rfidId:"id one", ownedBy:person1).save()
    RfidCard rfidcard2 = new RfidCard(rfidId:"135aa1fd", ownedBy:person2).save()

    //Set up the RFID Reader
    Location locationForRfidReader1 = new Location(xyLocation:"Here", cleartext:"Inngang sal 5").save()
    ClientApplication myApplication = new ClientApplication(about:"CompanyXXx's SuperDuper registration app").save()
    RfidReader rfidReader1 = new RfidReader(readerId:"Gunnars orange registreringsleser", readerLocation:locationForRfidReader1, usedByClientApplication:myApplication).save()




    new RfidRegistration(rfidCard:rfidcard1, registeredBy:rfidReader1, timestamp:new Date()).save()
    new RfidRegistration(rfidCard:rfidcard2, registeredBy:rfidReader1, timestamp:new Date()).save()

  }
  def destroy = {
  }
}