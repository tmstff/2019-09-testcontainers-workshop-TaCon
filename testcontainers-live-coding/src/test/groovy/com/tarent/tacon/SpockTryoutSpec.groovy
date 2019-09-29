package com.tarent.tacon

import spock.lang.Specification

class SpockTryoutSpec extends Specification {

    def "can determine length of String"() {
        given:
        def someString = "1234"

        when:
        def length = someString.length()

        then:
        length == 4
    }

}
