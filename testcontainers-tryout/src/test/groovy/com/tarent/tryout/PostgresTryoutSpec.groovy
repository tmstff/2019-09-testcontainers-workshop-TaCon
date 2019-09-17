package com.tarent.tryout

import java.sql.DriverManager

class PostgresTryoutSpec {

    // PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer()

    def "database is accessible"() {

        given: "a jdbc connection"
        def url = "???"
        def connection = DriverManager.getConnection(url, "foo", "secret")

        when: "querying the database"

        then: "result is returned"
    }

}
