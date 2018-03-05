package ejintegradorgrails

class PaymentMethod {

    String paymentId
    String name
    String paymentType

    static constraints = {
        name nullable: false, blank: false
    }


    @Override
    public String toString() {
        return name
    }
}
