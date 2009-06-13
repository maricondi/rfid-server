package clientdomain

import clientdomain.Location

class LocationController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ locationInstanceList: Location.list( params ), locationInstanceTotal: Location.count() ]
    }

    def show = {
        def locationInstance = Location.get( params.id )

        if(!locationInstance) {
            flash.message = "Location not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ locationInstance : locationInstance ] }
    }

    def delete = {
        def locationInstance = Location.get( params.id )
        if(locationInstance) {
            try {
                locationInstance.delete(flush:true)
                flash.message = "Location ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Location ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Location not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def locationInstance = Location.get( params.id )

        if(!locationInstance) {
            flash.message = "Location not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ locationInstance : locationInstance ]
        }
    }

    def update = {
        def locationInstance = Location.get( params.id )
        if(locationInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(locationInstance.version > version) {
                    
                    locationInstance.errors.rejectValue("version", "location.optimistic.locking.failure", "Another user has updated this clientdomain.Location while you were editing.")
                    render(view:'edit',model:[locationInstance:locationInstance])
                    return
                }
            }
            locationInstance.properties = params
            if(!locationInstance.hasErrors() && locationInstance.save()) {
                flash.message = "Location ${params.id} updated"
                redirect(action:show,id:locationInstance.id)
            }
            else {
                render(view:'edit',model:[locationInstance:locationInstance])
            }
        }
        else {
            flash.message = "Location not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def locationInstance = new Location()
        locationInstance.properties = params
        return ['locationInstance':locationInstance]
    }

    def save = {
        def locationInstance = new Location(params)
        if(!locationInstance.hasErrors() && locationInstance.save()) {
            flash.message = "Location ${locationInstance.id} created"
            redirect(action:show,id:locationInstance.id)
        }
        else {
            render(view:'create',model:[locationInstance:locationInstance])
        }
    }
}
