import org.openqa.selenium.Capabilities
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.RemoteWebDriver
import org.testcontainers.containers.BrowserWebDriverContainer

println("geb.env=${System.getProperty('geb.env')}")

host = '172.17.0.1' // docker network interface
baseUrl = "http://$host:8090/"

class BrowserWebDriverContainerClone {//extends DockerComposeContainer {

    Capabilities capabilities

    BrowserWebDriverContainerClone withCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities
        this
    }

    def start() {}

    RemoteWebDriver getWebDriver() {
        //def port = withExposedService("selenium-hub_1", 4444).getServicePort("selenium-hub_1", 4444)
        new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities)
    }
}

//driver = 'chrome'
driver = {
    def container = new BrowserWebDriverContainerClone()
            .withCapabilities(new ChromeOptions())
    container.start()
    container.getWebDriver()
}