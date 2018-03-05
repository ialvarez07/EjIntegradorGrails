package ejintegradorgrails

import grails.gorm.transactions.Transactional

@Transactional
class PaymentMethodService {

    def httpUrlConnectionService
    def serviceMethod() {

    }

    def getPaymentMethods(){
        def url = new URL("https://api.mercadolibre.com/sites/MLA/payment_methods/")
        def data  = httpUrlConnectionService.connection(url)
        def paymentMethodList = []
        data.findAll() {
            it.payment_type_id == "ticket"
        }each {
            it-> paymentMethodList.add(new PaymentMethod(paymentId: it.id, name:it.name, paymentType: it.payment_type_id))
        }
        return paymentMethodList
    }
}