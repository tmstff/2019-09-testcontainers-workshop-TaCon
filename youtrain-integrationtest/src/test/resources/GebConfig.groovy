import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxOptions
import org.testcontainers.containers.BrowserWebDriverContainer

println("geb.env=${System.getProperty('geb.env')}")

host = '172.17.0.1' // docker network interface
baseUrl = "http://$host:8090/"

driver = {
    def container = new BrowserWebDriverContainer()
            .withCapabilities(new ChromeOptions())
    container.start()
    container.getWebDriver()
}

environments {
    firefox {
        println "using FIREFOX"
        driver = {
            def container = new BrowserWebDriverContainer()
                    .withCapabilities(new FirefoxOptions())
            container.start()
            container.getWebDriver()
        }
    }
}