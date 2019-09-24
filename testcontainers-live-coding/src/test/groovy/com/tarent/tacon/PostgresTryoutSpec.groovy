package com.tarent.tacon


import java.sql.DriverManager

class PostgresTryoutSpec {

    // PostgreSQLContainer postgres = new PostgreSQLContainer()

    def "database is accessible"() {

        given: "a jdbc connection"
        def url = "???"
        def connection = DriverManager.getConnection(url, "foo", "secret")

        when: "querying the database"

        then: "result is returned"
        connection != null
    }

}
