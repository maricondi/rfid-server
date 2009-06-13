import clientdomain.Location

class BootStrap {

  def init = {servletContext ->
    def locationForRfid1 = new Location(xyLocation:"Here", cleartext:"not so clear").save()
    def rfidcard1 = new RfidCard(rfidId:"id one").save()
    def person1 = new Person(fullName: "Roger Moore", email: "roger@moore.com", company: "roger more", isHero: false, alumni: false, rfidCards: [rfidcard1]).save()
  }
  def destroy = {
  }
}