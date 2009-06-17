<%=packageName ? "package ${packageName}\n\n" : ''%>

import grails.converters.XML
import grails.converters.JSON

class ${className}Controller {

//Use ?format=xml to handle other content types


  def index = { redirect(action:list,params:params) }

  // the delete, save and update actions only accept POST requests
  static allowedMethods = [delete:'POST', save:'POST', update:'POST']

  def list = {
    withFormat {
      html {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ ${propertyName}List: ${className}.list( params ), ${propertyName}Total: ${className}.count() ]
      }
      xml { render ${className}.list(params) as XML }
      json { render ${className}.list(params) as JSON }
    }
  }

  def show = {
    def ${propertyName} = ${className}.get( params.id )

    if(!${propertyName}) {
      withFormat {
        html {
          flash.message = "${className} not found with id \${params.id}"
          redirect(action:list)
        }
        xml { render ${className} as XML }
        json { render ${className} as JSON }
      }
    }
    else {
      withFormat {
        html { return [ ${propertyName} : ${propertyName} ] }
        xml { sendNotFoundResponse("${className} not found with id: \${params.id}") }
      }
    }
  }


    def delete = {
        def ${propertyName} = ${className}.get( params.id )
        if(${propertyName}) {
            try {
                ${propertyName}.delete(flush:true)
                flash.message = "${className} \${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${className} \${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "${className} not found with id \${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def ${propertyName} = ${className}.get( params.id )

        if(!${propertyName}) {
            flash.message = "${className} not found with id \${params.id}"
            redirect(action:list)
        }
        else {
            return [ ${propertyName} : ${propertyName} ]
        }
    }

    def update = {
        def ${propertyName} = ${className}.get( params.id )
        if(${propertyName}) {
            if(params.version) {
                def version = params.version.toLong()
                if(${propertyName}.version > version) {
                    <%def lowerCaseName = grails.util.GrailsNameUtils.getPropertyName(className)%>
                    ${propertyName}.errors.rejectValue("version", "${lowerCaseName}.optimistic.locking.failure", "Another user has updated this ${className} while you were editing.")
                    render(view:'edit',model:[${propertyName}:${propertyName}])
                    return
                }
            }
            ${propertyName}.properties = params
            if(!${propertyName}.hasErrors() && ${propertyName}.save()) {
                flash.message = "${className} \${params.id} updated"
                redirect(action:show,id:${propertyName}.id)
            }
            else {
                render(view:'edit',model:[${propertyName}:${propertyName}])
            }
        }
        else {
            flash.message = "${className} not found with id \${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def ${propertyName} = new ${className}()
        ${propertyName}.properties = params
        return ['${propertyName}':${propertyName}]
    }

    def save = {
        def ${propertyName} = new ${className}(params)
        if(!${propertyName}.hasErrors() && ${propertyName}.save()) {
            flash.message = "${className} \${${propertyName}.id} created"
            redirect(action:show,id:${propertyName}.id)
        }
        else {
            render(view:'create',model:[${propertyName}:${propertyName}])
        }
    }

  private def sendNotFoundResponse(String myMessage) {
    response.status = 404
    render contentType: "application/xml", {
      errors {
        message(myMessage)
      }
    }
  }
}
