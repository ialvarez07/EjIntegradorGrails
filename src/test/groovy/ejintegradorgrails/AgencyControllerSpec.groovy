package ejintegradorgrails

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import grails.validation.ValidationException
import spock.lang.*

class AgencyControllerSpec extends Specification implements ControllerUnitTest<AgencyController>, DomainUnitTest<Agency> {


    def setup(){

    }

    def doWithSprings(){
        agencyService(AgencyService)
    }

    def 'Test que el index traiga los metodos de pago'(){
        given:
        controller.paymentMethodService = Mock(PaymentMethodService){
            getPaymentMethods() >> new PaymentMethod(id: "rapipago",name: "Rapi pago", paymentType: "ticket")
        }
        when:
        controller.index()
        then:
        model.paymentMethodList
        view == 'index'
    }

    def 'Test para buscar las agencias mas cercanas '(){
        given:

        controller.agencyService = Mock(AgencyService){
            buscarAgenciasCercanas("Cordoba, Argentina", "rapipago",1500) >>
                    [agencies:new Agency(agencyCode: "1", description: "Prueba", distance: "500",
                            address: new Address(location: new Location(lat:"-64.1810500",lng:"-31.4135000"), addressLine: "Independencia",
                                    city: "Cordoba", country: "Argentina", otherInfo: "otra info",
                                    state: "Cordoba", zipCode: "5000")),location: new Location(lat:"-64.1810500",lng:"-31.4135000")]

        }
        controller.params.address = "Cordoba, Argentina"
        controller.params.distance = "1500"
        controller.params.paymentSelect = "rapipago"
        when:
        controller.agencias()
        then:
        model.agenciesList
        view == 'agencias'
    }
}






