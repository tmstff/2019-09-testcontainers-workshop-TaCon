import org.testcontainers.containers.GenericContainer

GenericContainer apache =
        new GenericContainer("httpd:2.4")
                .withExposedPorts(80)

apache.start()

println("""Accessible via 
    http://localhost:${apache.getMappedPort(80)}""")

System.in.newReader().readLine()

apache.stop()