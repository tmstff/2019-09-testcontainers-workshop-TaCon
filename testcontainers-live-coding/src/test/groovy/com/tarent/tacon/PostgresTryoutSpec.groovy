package com.tarent.tacon

import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification

import java.sql.DriverManager

@Testcontainers
class PostgresTryoutSpec extends Specification{

    PostgreSQLContainer postgres = new PostgreSQLContainer().withUsername("foo").withPassword("secret")

    def "database is accessible"() {

        given: "a jdbc connection"
        def url = postgres.getJdbcUrl()
        def connection = DriverManager.getConnection(url, "foo", "secret")

        when: "querying the database"
        def statement = connection.prepareStatement("select 1")
        statement.execute()
        def result = statement.getResultSet()

        then: "result is returned"
        result.next()
        result.getInt(1) == 1
        connection != null
    }

}
