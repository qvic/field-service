package com.ips42.fieldservice.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity
@Table(name="Fields")
public class Field {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name="tenantId")
    private Long tenantId;
    @Column(name="fieldId")
    private Long fieldId;
    @Column(name="arrayGIS")
    private Object arrayGIS;
    @Column(name="createdOn")
    private Date createdOn;
    @Column(name="createdBy")
    private String createdBy;
    @Column(name="modifiedOn")
    private Date modifiedOn;
    @Column(name="modifiedBy")
    private String modifiedBy;
    public Field() {}
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

    public Object getArrayGIS() {
        return arrayGIS;
    }

    public void setArrayGIS(Object arrayGIS) {
        this.arrayGIS = arrayGIS;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }






}