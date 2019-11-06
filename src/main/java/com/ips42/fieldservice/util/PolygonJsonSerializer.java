package com.ips42.fieldservice.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.geojson.GeoJsonWriter;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class PolygonJsonSerializer extends JsonSerializer<Polygon> {

    private static GeoJsonWriter geoJsonWriter = new GeoJsonWriter();

    @Override
    public void serialize(Polygon value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String json = geoJsonWriter.write(value);
        gen.writeRawValue(json);
    }
}
