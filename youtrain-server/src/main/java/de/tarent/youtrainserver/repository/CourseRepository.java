package de.tarent.youtrainserver.repository;

import de.tarent.youtrainserver.entity.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

/**
 * The course repository with some custom queries.
 *
 * @author Mark Vz
 */
// This will be AUTO IMPLEMENTED by Spring into a Bean called courseRepository
public interface CourseRepository extends CrudRepository<Course, Integer> {
    @Query("SELECT c FROM Course c WHERE LOWER(c.title) LIKE LOWER(concat('%', :q, '%')) OR LOWER(c.description) LIKE LOWER(concat('%', :q, '%')) OR LOWER(c.teacher) LIKE LOWER(concat('%', :q, '%')) OR LOWER(c.price) LIKE LOWER(concat('%', :q, '%'))")
    Iterable<Course> findAllLikeSearch(@Param("q") String searchText);

    @Query("SELECT DISTINCT(c) FROM Course c JOIN CourseDate d ON d.course.id = c.id WHERE d.begin >= :begin AND d.begin <= :end AND d.end >= :begin AND d.end <= :end")
    Iterable<Course> findAllInRange(@Param("begin") Timestamp begin, @Param("end") Timestamp end);
}
