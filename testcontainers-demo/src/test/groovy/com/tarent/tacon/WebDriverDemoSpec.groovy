package com.tarent.tacon

import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver
import org.testcontainers.containers.BrowserWebDriverContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification

@Testcontainers
class WebDriverDemoSpec extends Specification {

    BrowserWebDriverContainer chrome = new BrowserWebDriverContainer()
        .withCapabilities(new ChromeOptions())
        //.withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL, new File("./"))
        //.withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.SKIP, null)
        //.withNetwork(Network.SHARED)
        //.withNetworkAliases("vnchost")

    /*public VncRecordingContainer vnc = new VncRecordingContainer(chrome)

    def setup() {
        chrome.start()
        vnc.start()
    }

    def cleanup() {
        vnc.saveRecordingToFile(new File("test.flv"))
        vnc.stop()
        chrome.stop()
    }*/

    def "Wikipedia Test" () {
        given:
        RemoteWebDriver driver = chrome.getWebDriver()
        driver.get("https://wikipedia.org")

        when:
        WebElement searchInput = driver.findElementByName("search")

        searchInput.sendKeys("Rick Astley")
        searchInput.submit()

        then:
        driver.getCurrentUrl() == "https://en.wikipedia.org/wiki/Madonna"

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
    }

}
