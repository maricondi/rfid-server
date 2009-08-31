package jzdomain

class Person {
  String fullName
  String email
  String company
  String telephone// (restricted)
  Boolean isHero = false
  Boolean alumni = false

  static hasMany = [ rfidCards : RfidCard ]

  static constraints = {
    fullName(blank:false)
    email(blank:false, email:true)
    company(blank:true)
	telephone(matches:"(\\+[0-9][0-9]?\\s?)?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]")
  }
  String toString() {"$fullName"}

}
