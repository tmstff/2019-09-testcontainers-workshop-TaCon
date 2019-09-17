package util


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.init.ScriptUtils
import org.testcontainers.containers.GenericContainer

import javax.sql.DataSource
import java.sql.Connection

trait DB {

    GenericContainer  mariaDB

    @Autowired
    DataSource dataSource

    @Autowired
    ResourceLoader resourceLoader

    @Autowired
    JdbcTemplate jdbcTemplate

    def resetData() {
        jdbcTemplate.execute('DELETE FROM course')
        jdbcTemplate.execute('DELETE FROM course_date')
        withConnection {
            connection ->
                Resource
                ScriptUtils.executeSqlScript(
                        connection,
                        resourceLoader.getResource('classpath:db/migration/V1.0.1__insert_courses.sql'))
                ScriptUtils.executeSqlScript(
                        connection,
                        resourceLoader.getResource('classpath:db/migration/V1.0.2__insert_course_dates.sql'))
        }
    }

    def withConnection(Closure<Connection> closure) {
        def connection = dataSource.getConnection()
        try {
            closure( connection )
        }
        finally {
            try {
                connection.close()
            } catch (Exception e) {
                // Ignore
            }
        }
    }

}