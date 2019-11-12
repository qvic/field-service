package com.ips42.fieldservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Measurement {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer tenantId;

    @Column
    private Integer fieldId;

    @Column
    private Integer measureId;

    @Column
    private Integer deviceId;

    @Column
    private String measuresJson;

    @Column
    private Double longitude;

    @Column
    private Double latitude;

    @Column
    private Instant date;
}