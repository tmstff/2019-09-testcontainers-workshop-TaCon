package de.tarent.youtrainserver.controller;

import de.tarent.youtrainserver.common.exception.AlreadyFullyBookedException;
import de.tarent.youtrainserver.entity.CourseDate;
import de.tarent.youtrainserver.repository.CourseDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * The REST API controller for courseDate.
 *
 * @author Mark Vz
 */
@RestController
@RequestMapping(path = "/course-dates")
public class CourseDateController {
    @Autowired
    private CourseDateRepository courseDateRepository;


    @Transactional
    @PutMapping(path = "{id}/book")
    public @ResponseBody
    Optional<CourseDate> bookById(@PathVariable("id") Integer id) {
        // TODO: make thread-safe
        final Optional<CourseDate> course = courseDateRepository.findById(id);
        if (course.isPresent()) {
            final CourseDate cd = course.get();
            final Integer amount = cd.getAmount();
            if (amount > 0) {
                cd.setAmount(amount - 1);
                return Optional.of(courseDateRepository.save(cd));
            } else {
                throw new AlreadyFullyBookedException("Booking not possible. Course is already fully booked.");
            }
        }
        return Optional.empty();
    }
}
