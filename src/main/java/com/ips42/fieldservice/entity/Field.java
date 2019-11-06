package com.ips42.fieldservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.Polygon;

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
    private int id;

    @Column
    private int tenantId;

    @Column
    private int fieldId;

    @Column(columnDefinition = "geography(Polygon,4326)")
    private Polygon<G2D> gisPolygon;

    @Column
    private Instant createdOn;

    @Column
    private String createdBy;

    @Column
    private Instant modifiedOn;

    @Column
    private String modifiedBy;
}