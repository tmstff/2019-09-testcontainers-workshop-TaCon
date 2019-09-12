package de.tarent.youtrainserver.entity;

import java.math.BigDecimal;

/**
 * A facade hiding courseDate info.
 *
 * Note: This interface is not used yet.
 *
 * @author Mark Vz
 */
public interface ShallowCourse {
    Integer getId();
    void setId(Integer id);

    String getTitle();
    void setTitle(String title);

    String getDescription();
    void setDescription(String description);

    String getTeacher();
    void setTeacher(String teacher);

    BigDecimal getPrice();
    void setPrice(BigDecimal price);
}
