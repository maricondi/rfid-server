package jzdomain

import java.util.Random

class Person {
//  int id
  String fullName
  String email
  String company
  String telephone
  Boolean isHero = false
  Boolean alumni = false



  static hasMany = [ rfidCards : RfidCard ]

  static constraints = {
    fullName(blank:false)
    email(blank:false, email:true)
    company(blank:true)
	telephone(blank:true, matches:"(\\+[0-9][0-9]?\\s?)?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]\\s?[0-9]")
  }
//  static mapping = { id generator:'assigned', params:[type:'int'] }

  String toString() {"$fullName"}

/*  def beforeInsert() {
     def r = new Random()
	 this.id = r.nextInt()
	 while (Person.get(id) != null){
	 	this.id = r.nextInt()
	 }
  }*/

  
}
