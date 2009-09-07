import au.com.bytecode.opencsv.CSVReader
import java.io.InputStreamReader
import java.io.BufferedReader
import jzdomain.Person

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
	*		Excpects a format of [company;name;status;rfidDec;rfid]
	*/
	def parsePeople(input){
		def reader = new CSVReader(new BufferedReader(new InputStreamReader(input)), ';' as char)
		def row = reader.readNext()
		while (row != null){
			println row
			//Person person1 = new Person(fullName: "Roger Moore", email: "roger@moore.com", company: "roger more", isHero: false, alumni: false, telephone:"+47 22 22 22 22").save()
			def p = new Person(company: row[0], fullName: row[1], isHero: row[2]=="hero", alumni:false, telephone:"", email:"test@test.com")
			p.save(flush:true)
			row = reader.readNext()
			
		}
		reader.close()
	}
	
}
