import geb.spock.GebSpec
import pages.CoursesPage
import pages.HomePage

class IntegrationSpec extends GebSpec {

    def 'Homepage is shown'() {
        when:
        to HomePage

        then:
        waitFor { at HomePage }
        coursesButtonInCard.isDisplayed()
        aboutButtonInCard.isDisplayed()
    }

    def 'Courses are displayed'() {
        given:
        to HomePage

        when:
        coursesButtonInCard.click()

        then:
        waitFor { at CoursesPage }
        searchResultTrs.size() == 6
    }

}