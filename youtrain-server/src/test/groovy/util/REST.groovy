package util

import groovyx.net.http.RESTClient
import org.springframework.boot.web.server.LocalServerPort

trait REST {

    @LocalServerPort
    int localPort

    RESTClient restClient() {
        def client = new RESTClient(localUrl())
        client.handler.failure = client.handler.success
        client
    }

    def localUrl() { "http://localhost:$localPort/" }

}
