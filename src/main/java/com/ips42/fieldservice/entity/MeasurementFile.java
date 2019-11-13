package com.ips42.fieldservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
public class MeasurementFile {

    @Id
    @Column
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean processed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeasurementFile that = (MeasurementFile) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
