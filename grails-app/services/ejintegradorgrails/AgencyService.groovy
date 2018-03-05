package ejintegradorgrails

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import javassist.NotFoundException

@Transactional
class AgencyService {
    def httpUrlConnectionService
    def messageSource

    def buscarAgenciasCercanas(String address,String paymentMethod, Integer distancia){
        Location location = buscarUbicacion(address)
        if (!location.lng || !location.lat){
            throw new IllegalArgumentException("No se encontró la ubicación especificada")
        }
        def url = new URL("https://api.mercadolibre.com/sites/MLA/payment_methods/${paymentMethod}/agencies?near_to=${location.lat},${location.lng},${distancia}")
        def data = httpUrlConnectionService.connection(url)

        def agencies = []

        data.results.each {
            it->
                 def agencyLocationList = it.address.location.split(",")
                 def agencyLocation = new Location(lat: agencyLocationList[0], lng:agencyLocationList[1])
                agencies.add(new Agency(agencyCode: it.agency_code, description: it.description, distance: it.distance,
                    address: new Address(location: agencyLocation, addressLine: it.address.address_line,
                    city: it.address.city, country: it.address.country, otherInfo: it.address.other_info,
                    state: it.address.state, zipCode: it.address.zip_code )))
        }
        return [agencies: agencies, location: location]
    }


    def buscarUbicacion(String address){
        def encode = URLEncoder.encode( address.toString(),"UTF-8" )
        def url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=${encode.toString()}&key=AIzaSyBXTadEqQfAquGVgWKScmjIDqHkEd0rlIw")
        def data = httpUrlConnectionService.connection(url)
        Location location = data.results.geometry.location
        return location
    }
}