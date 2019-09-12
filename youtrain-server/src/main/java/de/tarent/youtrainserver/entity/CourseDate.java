package de.tarent.youtrainserver.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * The courseDate entity. A courseDate belongs to exactly one course and does
 * not exist without it.
 *
 * @author Mark Vz
 */
@Entity
public class CourseDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Timestamp begin;
    private Timestamp end;
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;


    public CourseDate() {
    }

    public CourseDate(Timestamp begin, Timestamp end, Integer amount) {
        this.begin = begin;
        this.end = end;
        this.amount = amount;
    }

    public String getCourseNo() {
        return String.format("%s-%s", this.course.getId(), this.getId());
    }

    public Integer getCourseId() {
        return course.getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getBegin() {
        return begin;
    }

    public void setBegin(Timestamp begin) {
        this.begin = begin;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBegin(), getEnd(), getAmount());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof CourseDate) {
            final CourseDate other = (CourseDate) obj;
            return Objects.equals(begin, other.getBegin()) && Objects.equals(end, other.getEnd()) && Objects.equals(amount, other.getAmount());
        }

        return false;
    }
}
