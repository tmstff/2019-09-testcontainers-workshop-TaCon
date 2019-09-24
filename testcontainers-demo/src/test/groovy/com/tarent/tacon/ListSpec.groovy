package com.tarent.tacon

import spock.lang.Specification

class ListSpec extends Specification {

    def "Should be able to remove from list"() {
        given:
        def list = [1, 2, 3, 4]

        when:
        list.remove(0)

        then:
        list == [2, 3, 4]
    }

}