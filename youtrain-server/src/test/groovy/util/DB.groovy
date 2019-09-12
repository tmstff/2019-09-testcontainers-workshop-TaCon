package util

import org.spockframework.util.IoUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.init.ScriptUtils
import org.testcontainers.containers.GenericContainer
import org.testcontainers.images.builder.ImageFromDockerfile

import javax.sql.DataSource
import java.sql.Connection

import static java.lang.System.getenv
import static org.testcontainers.containers.wait.strategy.Wait.forHealthcheck

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

    def startDb() {
        if (useMariaDB()) {
            println('using Maria DB')
            mariaDB = new GenericContainer(
                    new ImageFromDockerfile()
                            .withFileFromClasspath("Dockerfile", "mariadb/Dockerfile")
            ).waitingFor(forHealthcheck())

            mariaDB.start()
            System.setProperty('spring.datasource.url', jdbcUrl())
        } else {
            println('using H2')
            System.setProperty("spring.datasource.driver-class-name", "org.h2.Driver")
            System.setProperty("spring.datasource.url", "jdbc:h2:mem:db;DB_CLOSE_DELAY=-1")
            System.setProperty("spring.datasource.username", "sa")
            System.setProperty("spring.datasource.password", "sa")
        }
    }

    def stopDb() {
        mariaDB?.stop()
    }

    String jdbcUrl() {
        def host = getenv('HOST_IP') ?: 'localhost'
        "jdbc:mysql://$host:${mariaDB.getMappedPort(3306)}/ta_youtrain"
    }

    static boolean useMariaDB() {
        def value = (System.getenv('useMariaDB') ?: System.getProperty('useMariaDB'))?.toLowerCase()
        println("useMariaDB=$value")
        value == "true"
    }

}