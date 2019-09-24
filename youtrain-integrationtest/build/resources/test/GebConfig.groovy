import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver

/*println("geb.env=${System.getProperty('geb.env')}")

host = System.getenv('HOST_IP') ?: System.getProperty('HOST_IP') ?: '172.17.0.1'

baseUrl = "http://$host:8090"
println "baseUrl=$baseUrl"

// default
driver = 'chrome'

//driver = 'firefox'

environments {
    firefoxGrid {
        println "using FIREFOX from selenium grid"
        driver = {
            new RemoteWebDriver(new URL(getHubUrl()), DesiredCapabilities.firefox())
        }
    }
    chromeGrid {
        println "using CHROME from selenium grid"
        driver = {
            new RemoteWebDriver(new URL(getHubUrl()), DesiredCapabilities.chrome())
        }
    }
}*/