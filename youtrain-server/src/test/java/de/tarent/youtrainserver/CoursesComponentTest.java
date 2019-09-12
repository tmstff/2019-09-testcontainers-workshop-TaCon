package de.tarent.youtrainserver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.tarent.youtrainserver.entity.Course;
import de.tarent.youtrainserver.entity.CourseDate;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.lang.System.getenv;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testcontainers.containers.wait.strategy.Wait.forHealthcheck;

/**
 * Integration test probing all CRUD actions via the REST API
 *
 * @author Mark Vz
 */
@Ignore
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = YoutrainServerApplication.class)
@ContextConfiguration(initializers = {CoursesComponentTest.Initializer.class})
public class CoursesComponentTest {

    @ClassRule
    public     static GenericContainer mariaDB =
            new GenericContainer(
                    new ImageFromDockerfile()
                            .withFileFromClasspath("Dockerfile", "mariadb/Dockerfile")
            ).waitingFor(forHealthcheck());

    @Autowired
    private MockMvc mockMvc;

    // Used for converting courses to/from JSON
    private static ObjectMapper mapper = new ObjectMapper();


    @BeforeClass
    public static void setup() {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Test
    public void shouldReturnAllCourses() throws Exception {
        this.mockMvc.perform(get("/courses"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(5))));
    }

    @Test
    public void shouldReturnAllCoursesMatchingTheSearchTextInLowerCase() throws Exception {
        // Returns three entities because of matching title
        this.mockMvc.perform(get("/courses?q=java"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[*].title", contains(
                        "Functional Programming in JavaScript",
                        "Lambdas in Java 8",
                        "The Java Streaming API"
                )));
    }

    @Test
    public void shouldReturnAllCoursesMatchingTheSearchTextInUpperCase() throws Exception {
        // Returns three entities because of matching title
        this.mockMvc.perform(get("/courses?q=JAVA"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[*].title", contains(
                        "Functional Programming in JavaScript",
                        "Lambdas in Java 8",
                        "The Java Streaming API"
                )));
    }

    @Test
    public void shouldReturnAllCoursesMatchingTheSearchTextInMixedCase() throws Exception {
        // Returns one entity because of matching teacher
        this.mockMvc.perform(get("/courses?q=AliCE"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[*].title", contains(
                        "Functional Programming in JavaScript"
                )));
    }

    @Test
    public void shouldCreateNewCourse() throws Exception {
        final String title = "Test Title 4711";
        final String description = "Test Description 4712";
        final String teacher = "Test Teacher 4713";
        final BigDecimal price = BigDecimal.valueOf(4711.42d);

        final Course course = new Course(title, description, teacher, price);
        final List<CourseDate> courseDates = Arrays.asList(
                new CourseDate(Timestamp.valueOf("2021-08-01 09:30:00"), Timestamp.valueOf("2021-08-01 18:30:00"), 15),
                new CourseDate(Timestamp.valueOf("2022-07-05 10:30:00"), Timestamp.valueOf("2022-07-07 14:30:00"), 25));
        course.setCourseDates(courseDates);

        final MvcResult result = this.mockMvc.perform(post("/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(course)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is(course.getTitle())))
                .andExpect(jsonPath("$.description", is(course.getDescription())))
                .andExpect(jsonPath("$.teacher", is(course.getTeacher())))
                .andExpect(jsonPath("$.price", is(course.getPrice().doubleValue())))
                .andExpect(jsonPath("$.courseDates", hasSize(2)))
                .andReturn();

        final Course parsedResponse = mapper.readValue(result.getResponse().getContentAsByteArray(), Course.class);

        assertThat(parsedResponse)
                .hasFieldOrPropertyWithValue("title", title)
                .hasFieldOrPropertyWithValue("description", description)
                .hasFieldOrPropertyWithValue("teacher", teacher)
                .hasFieldOrPropertyWithValue("price", price)
                .hasFieldOrPropertyWithValue("courseDates", courseDates);
    }

    @Test
    public void shouldUpdateCourse() throws Exception {
        final String newTitle = "My Super Test Title";
        final Timestamp now = Timestamp.valueOf(LocalDateTime.now());

        final MvcResult originalResult = this.mockMvc.perform(get("/courses/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        final Course parsedOriginal = mapper.readValue(originalResult.getResponse().getContentAsByteArray(), Course.class);
        parsedOriginal.getCourseDates().get(0).setBegin(now);

        final Course updatedCourse = mapper.readValue(originalResult.getResponse().getContentAsByteArray(), Course.class);
        updatedCourse.setTitle(newTitle);
        updatedCourse.getCourseDates().get(0).setBegin(now);

        final MvcResult result = this.mockMvc.perform(patch("/courses/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(updatedCourse)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        final Course parsedResponse = mapper.readValue(result.getResponse().getContentAsByteArray(), Course.class);

        assertThat(parsedResponse)
                .hasFieldOrPropertyWithValue("title", newTitle)
                .hasFieldOrPropertyWithValue("description", parsedOriginal.getDescription())
                .hasFieldOrPropertyWithValue("teacher", parsedOriginal.getTeacher())
                .hasFieldOrPropertyWithValue("price", parsedOriginal.getPrice())
                .hasFieldOrPropertyWithValue("courseDates", parsedOriginal.getCourseDates());
    }

    @Test
    public void shouldDeleteCourseAndCascadeDeletionForCourseDates() throws Exception {
        this.mockMvc.perform(delete("/courses/6"))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/courses/6"))
                .andExpect(status().isNotFound());

        // TODO: test deletion of course and course dates (delete cascade)
    }

    @Test
    public void shouldBookCourseDate() throws Exception {
        this.mockMvc.perform(put("/course-dates/1/book")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", is(9)));
    }

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            String host = Optional.ofNullable( getenv("HOST_IP") ).orElse("localhost");
            String jdbcUrl = "jdbc:mysql://" + host + ":" + mariaDB.getMappedPort(3306) + "/ta_youtrain";

            TestPropertyValues.of(
                    "spring.datasource.url=" + jdbcUrl
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

}
