package util

import groovyx.net.http.RESTClient

class RestClientUtil {
    static RESTClient restClient(url) {
        def client = new RESTClient(url)
        client.handler.failure = client.handler.success
        client
    }
}
