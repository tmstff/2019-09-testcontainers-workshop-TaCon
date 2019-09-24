package pages

import geb.Page

class CoursesPage extends Page {

    static url = "/courses"

    static at = {
        title == "YouTrain"
        contentRoot.isDisplayed()
    }

    static content = {
        contentRoot { $('#courses') }
        searchResultTrs { $('#courses #coursesTable tr.course') }
    }

}