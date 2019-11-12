package com.ips42.fieldservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.locationtech.jts.geom.Polygon;

import javax.persistence.*;
import java.time.Instant;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Field {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer tenantId;

    @Column
    private Integer fieldId;

    @Column(columnDefinition = "geometry(Polygon)")
    private Polygon gisPolygon;

    @Column
    private Instant createdOn;

    @Column
    private String createdBy;

    @Column
    private Instant modifiedOn;

    @Column
    private String modifiedBy;
}