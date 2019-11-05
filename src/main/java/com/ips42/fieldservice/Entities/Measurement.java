package com.ips42.fieldservice.Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Measurements")
public class Measurement {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name="tenantId")
    private Long tenantId;
    @Column(name="fieldId")
    private Long fieldId;
    @Column(name="deviceId")
    private Long deviceId;
    @Column(name="measureId")
    private Long measureId;
    @Column(name="measureArray")
    private Object measureArray;
    @Column(name="longitude")
    private float longitude;
    @Column(name="latitude")
    private float latitude;
    @Column(name="date")
    private Date date;


    public Measurement() {}

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getMeasureId() {
        return measureId;
    }

    public void setMeasureId(Long measureId) {
        this.measureId = measureId;
    }

    public Object getMeasureArray() {
        return measureArray;
    }

    public void setMeasureArray(Object measureArray) {
        this.measureArray = measureArray;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}