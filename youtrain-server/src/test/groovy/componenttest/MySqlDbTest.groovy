package componenttest

import de.tarent.youtrainserver.YoutrainServerApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(classes = YoutrainServerApplication.class, webEnvironment = RANDOM_PORT)
class MySqlDbTest extends Specification {

    @Autowired
    JdbcTemplate jdbcTemplate

    def "is mysql db"() {

        when:
        def result = jdbcTemplate.queryForList('''SELECT * FROM information_schema.ENGINES WHERE comment LIKE '%MySQL%' ''')

        then:
        result.size() >= 1
    }


}
