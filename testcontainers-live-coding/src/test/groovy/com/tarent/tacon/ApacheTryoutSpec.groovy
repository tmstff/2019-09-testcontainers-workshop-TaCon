package com.tarent.tacon

class ApacheTryoutSpec {

    //GenericContainer container = new GenericContainer("httpd").waitingFor(Wait.forHttp("/"))

    def "can access apache"() {
        given:
        String url = "???"

        expect:
        url =~ /http:\/\/.*:\d+/

        when:
        String result = httpGet(url)

        then:
        result.contains("It works!")
    }

    String httpGet(String url) {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection()
        connection.setRequestMethod("GET")
        assert connection.getResponseCode() == 200
        connection.getInputStream().text
    }

}
