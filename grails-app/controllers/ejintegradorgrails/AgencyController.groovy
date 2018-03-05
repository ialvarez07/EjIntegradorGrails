package ejintegradorgrails

import grails.validation.ValidationException
import javassist.NotFoundException

import static org.springframework.http.HttpStatus.*

class AgencyController {

    def agencyService
    def paymentMethodService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def index(){
        def paymentMethodList = paymentMethodService.getPaymentMethods()
        respond paymentMethodList:paymentMethodList
    }

    def agencias(){
        String address = params.address
        Integer distance = Integer.parseInt(params.distance)
        String paymentMethod = params.paymentSelect
        if(!address || !distance || !paymentMethod || distance <= 0){
            flash.message = message(code: 'agency.search.error.message')
            redirect action: "index", method: "GET"
            return
        }
        def agenciesList
        try{
            agenciesList = agencyService.buscarAgenciasCercanas(address, paymentMethod, distance)
        }catch(IllegalArgumentException e){
            flash.message = e.getMessage()
            redirect action: "index", method: "GET"
            return
        }
        respond agenciesList: agenciesList.getAt('agencies'), location: agenciesList.getAt('location')
    }
}
