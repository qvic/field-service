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
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column
    private int tenantId;

    @Column
    private int fieldId;

    @Column
    private int deviceId;

    @Column
    private int measureId;

    @Column
    private String measuresJson;

    @Column
    private float longitude;

    @Column
    private float latitude;

    @Column
    private Instant date;
}