package jzdomain


class Person {
  String fullName
  String email
  String company
  String telephone
  Boolean isHero = false
  Boolean alumni = false
  
  static hasMany = [ rfidCards : RfidCard ]

  static constraints = {
    fullName(blank:false)
    email(blank:true, email:true)
    company(blank:true)
	telephone(blank:true, matches:"(\\+[0-9][0-9]?\\s?)?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]")
  }

  String toString() {"$fullName"}


  static Person getByRfidId(String id){
	def card = RfidCard.findWhere(rfidId:id)
	return card != null ? card.ownedBy : null
}
}
