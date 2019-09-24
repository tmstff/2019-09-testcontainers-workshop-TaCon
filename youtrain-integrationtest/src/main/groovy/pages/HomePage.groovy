package pages

import geb.Page

class HomePage extends Page {
    static url = "/"

    static at = {
        title == "YouTrain"
        coursesButtonInCard.isDisplayed()
    }

    static content = {
        coursesButtonInCard { $('#cards a[href="/courses"]') }
        teachersButtonInCard { $('#cards a[href="/teachers"]') }
        aboutButtonInCard { $('#cards a[href="/about"]') }
    }

}