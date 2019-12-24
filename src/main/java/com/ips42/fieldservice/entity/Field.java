package com.ips42.fieldservice.entity;

import com.ips42.fieldservice.dto.CoordinateDto;
import com.ips42.fieldservice.dto.FieldDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.locationtech.jts.geom.Polygon;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Field {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String tenantId;

    @Column
    private String fieldId;

    @Column(columnDefinition = "geometry(Polygon)")
    private Polygon gisPolygon;

    @CreatedDate
    @Column
    private Instant createdOn;

    @CreatedBy
    @Column
    private String createdBy;

    @LastModifiedDate
    @Column
    private Instant modifiedOn;

    @LastModifiedBy
    @Column
    private String modifiedBy;

    public FieldDto toFieldDto() {
        FieldDto fieldDto = new FieldDto();

        fieldDto.setFieldId(fieldId);

        List<CoordinateDto> polygon = Arrays.stream(gisPolygon.getCoordinates())
                .map(coordinate -> {
                    CoordinateDto coordinateDto = new CoordinateDto();
                    coordinateDto.setLatitude(coordinate.x);
                    coordinateDto.setLongitude(coordinate.y);
                    return coordinateDto;
                }).collect(Collectors.toList());
        fieldDto.setPolygon(polygon);

        return fieldDto;
    }
}