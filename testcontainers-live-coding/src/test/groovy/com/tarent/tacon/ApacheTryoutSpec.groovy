package com.tarent.tacon

import org.testcontainers.containers.FixedHostPortGenericContainer
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

@Testcontainers
class ApacheTryoutSpec extends  Specification {

    @Shared
    GenericContainer container = new FixedHostPortGenericContainer("httpd")
            .withFixedExposedPort(8080,80)
            .waitingFor(Wait.forHttp("/"))
    def "can access apache"() {
        given:
        String url = "http://localhost:8080"


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
