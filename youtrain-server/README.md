# youtrain-server

A Spring Boot application providing a REST API with a couple of CRUD actions.
Data is persisted to a MySQL (MariaDB) database.

This application is the backend server for the Vue.js SPA called youtrain.

DB migration is done by Flyway (see Gradle tasks).

## Helpfull commands

Run unit tests:

    ./gradlew test
    open build/reports/tests/test/index.html            # reports anschauen
    google-chrome build/reports/tests/test/index.html   # alternativ direkt chrome aufrufen
    
If you want to use Maria DB instead of H2, you can use

    env useMariaDB=true ./gradlew clean test

or add JVM option ```-DuseMariaDB=true``` to the run configuration of the desired Test in your IDE. 

Start database:

    docker-compose up -d youtrain-db
    # maybe look into looks to see if Startup is finished:
    docker-compose logs -f

Start service via gradle (be sure a db is running, e.g. see above):

    ./gradlew bootRun
    
Build executable JAR:

    ./gradlew bootJar
    
Build docker image (requires executable jar, see above):

    docker build -t registry.gitlab.com/tarent/youtrain-server:latest .

Start service and database via docker-compose (may require a new image, see above):

    docker-compose up -d
    # maybe look into looks to see if Startup is finished:
    docker-compose logs -f

Start all with ELK logging:

    $(src/bin/set_host_ip.sh)
    docker-compose -f docker-compose-elk.yml up -d