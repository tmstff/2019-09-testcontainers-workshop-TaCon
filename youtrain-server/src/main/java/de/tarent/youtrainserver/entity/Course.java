package de.tarent.youtrainserver.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * The course entity. A course has many courseDates.
 *
 * @author Mark Vz
 */
@Entity
public class Course implements ShallowCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private String teacher;
    private BigDecimal price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CourseDate> courseDates;


    public Course() {
    }

    public Course(String title, String description, String teacher, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.teacher = teacher;
        this.price = price;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getTeacher() {
        return teacher;
    }

    @Override
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<CourseDate> getCourseDates() {
        return courseDates;
    }

    public void setCourseDates(final List<CourseDate> courseDates) {
        courseDates.forEach(cd -> {
            cd.setCourse(this);
        });
        this.courseDates = courseDates;
    }
}
