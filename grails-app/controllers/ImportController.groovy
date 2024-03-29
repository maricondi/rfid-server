import au.com.bytecode.opencsv.CSVReader
import java.io.InputStreamReader
import java.io.BufferedReader
import jzdomain.Person
import jzdomain.RfidCard

import java.math.BigInteger

import org.codehaus.groovy.grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class ImportController {
	
	// the save action only accept POST requests
    static allowedMethods = [save:'POST']
	
	
    def index = { 
		render(view:'upload')
	}
	

	def save = {
		println "Prøver å lese"
	    parsePeople(request.getFile("file").inputStream)
		redirect(action:index)
	}
	
	/**
	*		Excpects a format of [company;name;email;blank;rfidDecimal;blank;status]
	*/
	def parsePeople(input){
		def reader = new CSVReader(new BufferedReader(new InputStreamReader(input)), ';' as char, '"' as char)
		def row = reader.readNext()
		while (row != null){
			println row
			//Person person1 = new Person(fullName: "Roger Moore", email: "roger@moore.com", company: "roger more", isHero: false, alumni: false, telephone:"+47 22 22 22 22").save()
			def p = new Person(company: row[0], fullName: row[1], isHero: row[6]=="hero", alumni:false, telephone:"", email:row[2]).save(flush:true)
			String rfid = new BigInteger(row[4]).toString(16).padLeft(10, "0")
			
			RfidCard card = new RfidCard(rfidId:rfid, ownedBy:p).save()
			row = reader.readNext()
			
		}
		reader.close()
	}
	
}
