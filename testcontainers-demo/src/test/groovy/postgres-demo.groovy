import org.testcontainers.containers.PostgreSQLContainer


PostgreSQLContainer postgres =
        new PostgreSQLContainer()

postgres.start()

println("Accessible via ${postgres.getJdbcUrl()}")

System.in.newReader().readLine()

postgres.stop()
