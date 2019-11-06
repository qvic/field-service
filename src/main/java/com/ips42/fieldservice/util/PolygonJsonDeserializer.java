package com.ips42.fieldservice.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.geolatte.geom.Polygon;
import org.geolatte.geom.Position;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class PolygonJsonDeserializer<P extends Position> extends JsonDeserializer<Polygon<P>> {
    @Override
    public Polygon<P> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return null;
    }
}
