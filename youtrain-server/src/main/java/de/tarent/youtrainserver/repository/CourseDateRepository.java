package de.tarent.youtrainserver.repository;

import de.tarent.youtrainserver.entity.CourseDate;
import org.springframework.data.repository.CrudRepository;

/**
 * The courseDate repository.
 *
 * @author Mark Vz
 */
public interface CourseDateRepository extends CrudRepository<CourseDate, Integer> {
}
