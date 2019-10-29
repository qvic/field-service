package com.ips42.fieldservice.model;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class Field {

    private long id;
    private long fieldId;
    private List<String> vertices;

    public Field(long id, long fieldId, List<String> vertices) {
        this.id = id;
        this.fieldId = fieldId;
        this.vertices = vertices;
    }
}
