package com.ips42.fieldservice.dto;

import com.ips42.fieldservice.entity.Field;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;

import java.time.Instant;
import java.util.List;

@ToString
@Getter
@Setter
public class FieldDto {

    private String fieldId;
    private List<CoordinateDto> polygon;

    public Field toField(String tenantId, String createdBy, Instant createdOn) {
        Field field = new Field();

        field.setFieldId(fieldId);
        field.setTenantId(tenantId);
        field.setCreatedBy(createdBy);
        field.setCreatedOn(createdOn);

        Coordinate[] coordinates = polygon.stream()
                .map(c -> new Coordinate(c.getLatitude(), c.getLongitude()))
                .toArray(Coordinate[]::new);
        field.setGisPolygon(new GeometryFactory().createPolygon(coordinates));

        return field;
    }
}
