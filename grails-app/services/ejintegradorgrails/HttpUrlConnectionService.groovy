package ejintegradorgrails

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper

@Transactional
class HttpUrlConnectionService {

    def serviceMethod() {

    }

    def connection(URL url){
        def connection = (HttpURLConnection)url.openConnection()
        connection.setRequestMethod("GET")
        connection.setRequestProperty("Accept", "application/json")
        JsonSlurper json = new JsonSlurper()
        return json.parse(connection.getInputStream())
    }
}
