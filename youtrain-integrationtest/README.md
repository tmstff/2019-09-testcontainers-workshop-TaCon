# youtrain-integrationtest

## Run Tests

You can run the tests either using a locally installed Webdriver oder using a Webdriver from a
Selenium Grid running in docker compose. A Webdriver is required by the tests for browser automation.

## Running with local Webdriver

For local development it's useful to install the chrome and / or firefox WebDrivers:

* https://sites.google.com/a/chromium.org/chromedriver/downloads
    * Debian & Co: ```sudo apt-get install chromedriver```
    * Mac: ```brew tap homebrew/cask && brew cask install chromedriver```
* https://github.com/mozilla/geckodriver/releases

You can run the tests from your IDE or via command line:

    ./gradlew test
 
By default the local Chrome Webdriver will be used. If you want to use another Webdriver,
you can configure it in [src/test/resources/GebConfig.groovy](src/test/resources/GebConfig.groovy) by changing the value for ```driver``` 
(e.g. ```driver = 'firefox'```).

## Running on Selenium Grid

First you need to start the Selenium Grid:

    docker-compose up -d
    
Use VM options ```geb.env=chromeGrid``` or ```geb.env=firefoxGrid``` to use Selenium Grid for the tests,
e.g.

    $(./bin/set_host_ip.sh) # Run for each new shell and after each network interface change
    env "geb.env=chromeGrid" ./gradlew test
    
in case of problems with the dot in the variable name use the following instead:
    
    $(./bin/set_host_ip.sh) # Run for each new shell and after each network interface change
    geb_env=chromeGrid; ./gradlew test
    
To use Selenium Grid from the IDE, you can give the following extra VM option:

    -Dgeb.env=chromeGrid -DHOST_IP=w.x.y.z

## Technologies

* http://www.groovy-lang.org/ - The Groovy Language
* http://spockframework.org/ - Spock Testing Framework
* http://www.gebish.org/ - Geb UI Testing DSL
* Selenium
    * https://docs.seleniumhq.org/ - Browser Test Automation
    * https://github.com/SeleniumHQ/docker-selenium - Selenium Grid in Docker
