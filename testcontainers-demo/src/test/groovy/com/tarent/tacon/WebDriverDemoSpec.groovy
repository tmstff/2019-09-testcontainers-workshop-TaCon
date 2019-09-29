package com.tarent.tacon

import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import org.testcontainers.containers.BrowserWebDriverContainer
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification

class BrowserWebDriverContainerClone {//extends DockerComposeContainer {

    Capabilities capabilities


    BrowserWebDriverContainerClone withCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities
        this
    }

    RemoteWebDriver getWebDriver() {
        //def port = withExposedService("selenium-hub_1", 4444).getServicePort("selenium-hub_1", 4444)
        new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities)
    }
}


@Testcontainers
class WebDriverDemoSpec extends Specification {

    BrowserWebDriverContainerClone chrome = new BrowserWebDriverContainerClone()
        .withCapabilities(new ChromeOptions())

    def "Wikipedia Test" () {
        given:
        RemoteWebDriver driver = chrome.getWebDriver()
        driver.get("https://wikipedia.org")

        when:
        WebElement searchInput = driver.findElementByName("search")

        searchInput.sendKeys("Rick Astley")
        searchInput.submit()

        then:
        driver.getCurrentUrl() == "https://en.wikipedia.org/wiki/Rick_Astley"

        when:
        WebElement otherPage = driver.findElementByLinkText("Rickrolling")
        otherPage.click()

        then:
        def text = driver.findElementsByCssSelector("p")
                .find() { it.getText().contains("meme") }
                ?.getText()
        assert text != null
        assert text.contains("Rickrolling, alternatively rick-rolling, is a prank and an Internet meme involving an unexpected " +
                        "appearance of the music video for the 1987 Rick Astley song \"Never Gonna Give You Up\".")

        cleanup:
        driver.close()
    }

}
