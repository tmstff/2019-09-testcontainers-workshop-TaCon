import org.testcontainers.containers.PostgreSQLContainer


PostgreSQLContainer postgres =
        new PostgreSQLContainer()

postgres.start()

postgres.getJdbcUrl()

println("Accessible via ${postgres.getJdbcUrl()}")

System.in.newReader().readLine()

postgres.stop()
