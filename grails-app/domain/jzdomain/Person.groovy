package jzdomain

class Person {
  String fullName
  String email
  String company
  //Int telephone (restricted)
  Boolean isHero = false
  Boolean alumni = false

  static hasMany = [ rfidCards : RfidCard ]

  static constraints = {
    fullName(blank:false)
    email(blank:false, email:true)
    company(blank:true)
  }
  String toString() {"$fullName"}

}