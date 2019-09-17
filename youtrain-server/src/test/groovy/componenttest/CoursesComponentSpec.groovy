package componenttest

import de.tarent.youtrainserver.YoutrainServerApplication
import de.tarent.youtrainserver.entity.CourseDate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import spock.lang.Specification
import spock.lang.Unroll
import util.DB
import util.REST

import java.sql.Timestamp

import static groovy.json.JsonOutput.toJson
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(classes = YoutrainServerApplication.class, webEnvironment = RANDOM_PORT)
class CoursesComponentSpec extends Specification implements DB, REST {

    def setup() {
        resetData()
    }

    def "should return all courses"() {
        when:
        def response = restClient().get(path: "/courses")

        then:
        response.status == 200
        response.data.size == 6
    }

    @Unroll
    def "should return all courses matching #searchTerm"() {
        when:
        def response = restClient().get(path: "/courses", query: [q: searchTerm])

        then:
        with(response) {
            status == 200
            data.size == 3
            data[0].title == 'Functional Programming in JavaScript'
            data[1].title == 'Lambdas in Java 8'
            data[2].title == 'The Java Streaming API'
        }

        where:
        searchTerm << ['java', 'JaVa', 'JAVA']
    }

    def "should create new course"() {
        given:
        def newCourse = [
            title: "Test Title 4711",
            description: "Test Description 4712",
            teacher: "Test Teacher 4713",
            price: BigDecimal.valueOf(4711.42d),
            courseDates: [
                new CourseDate(Timestamp.valueOf("2021-08-01 09:30:00"), Timestamp.valueOf("2021-08-01 18:30:00"), 15),
                new CourseDate(Timestamp.valueOf("2022-07-05 10:30:00"), Timestamp.valueOf("2022-07-07 14:30:00"), 25)
            ]
        ]

        when:
        def response = restClient().post(
                path: "/courses",
                body: toJson(newCourse),
                contentType: 'application/json')

        then:
        response.status == 201
        with (response.data) {
            title == newCourse.title
            description == newCourse.description
            teacher == newCourse.teacher
            price == newCourse.price
            courseDates.size() == 2
        }
    }

    def "should update course"() {
        given:
        def existingCourse = restClient().get(path: "/courses/1").data

        and:
        def updateCourseDates = existingCourse.courseDates.clone()
        updateCourseDates[0].begin = '1970-01-01T00:00:00.000+0000'
        def updatedCourse = existingCourse + [
            title: "My Super Test Title",
            courseDates: updateCourseDates
        ]

        when:
        def response = restClient().patch(
                path: "/courses/1",
                body: toJson(updatedCourse),
                contentType: 'application/json')

        then:
        response.status == 200
        response.data == updatedCourse
    }

    def "should delete course"() {
        when:
        def response = restClient().delete(path: "/courses/6")

        then:
        response.status == 200
        restClient().get(path: "/courses/6").status == 404
    }

    def "should book course"() {
        when:
        def response = restClient().put(path: "/course-dates/1/book")

        then:
        response.status == 200
        response.data.amount == 9
    }

}
