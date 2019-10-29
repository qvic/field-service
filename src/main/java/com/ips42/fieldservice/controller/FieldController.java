package com.ips42.fieldservice.controller;

import com.ips42.fieldservice.model.Field;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class FieldController {

    private Map<Long, Field> data = new ConcurrentHashMap<>();

    @GetMapping("/field/{fieldId}")
    public ResponseEntity<Field> getField(@PathVariable long fieldId) {
        Field field = data.get(fieldId);
        if (Objects.isNull(field)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(field, HttpStatus.OK);
    }

    @PostMapping("/field")
    public ResponseEntity<Field> createField(@RequestBody Field field) {
        data.put(field.getFieldId(), field);
        return new ResponseEntity<>(field, HttpStatus.OK);
    }

    @GetMapping("/fields")
    public ResponseEntity<List<Field>> getFields() {
        return new ResponseEntity<>(List.copyOf(data.values()), HttpStatus.OK);
    }

}