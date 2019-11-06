package com.ips42.fieldservice.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.geolatte.geom.Polygon;
import org.geolatte.geom.Position;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class PolygonJsonSerializer<P extends Position> extends JsonSerializer<Polygon<P>> {
    @Override
    public void serialize(Polygon<P> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("body", value.toString());
        gen.writeEndObject();
    }
}
