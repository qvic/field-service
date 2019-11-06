package com.ips42.fieldservice.controller;

import com.ips42.fieldservice.entity.Field;
import com.ips42.fieldservice.repository.FieldRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
public class FieldController {

    private FieldRepository fieldRepository;

    public FieldController(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    @GetMapping("/fields")
    public ResponseEntity<Iterable<Field>> getAll(@RequestHeader int tenantId) {
        return ResponseEntity.ok(fieldRepository.findByTenantId(tenantId));
    }

    @GetMapping("/fields/{fieldId}/area")
    public ResponseEntity<Map<String, Object>> getByAreaFieldId(@PathVariable int fieldId, @RequestHeader int tenantId) {
        Optional<Double> areaByFieldId = Optional.ofNullable(fieldRepository.getAreaByFieldId(tenantId, fieldId));

        if (areaByFieldId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(Map.of("area", areaByFieldId.get()));
    }

    @GetMapping("/fields/{fieldId}")
    public ResponseEntity<Field> getByFieldId(@PathVariable int fieldId, @RequestHeader int tenantId) {
        return ResponseEntity.of(fieldRepository.findByTenantIdAndFieldId(tenantId, fieldId));
    }

    @PostMapping("/fields")
    public ResponseEntity<Field> createField(@RequestBody Field field, @RequestHeader int tenantId) {
        return ResponseEntity.ok(fieldRepository.save(field));
    }

    @PutMapping("/fields/{fieldId}")
    public ResponseEntity<Field> updateField(@PathVariable int fieldId, @RequestBody Field field, @RequestHeader int tenantId) {
        Optional<Field> fieldById = fieldRepository.findByTenantIdAndFieldId(tenantId, fieldId);
        if (fieldById.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        field.setId(fieldId);
        return ResponseEntity.ok(fieldRepository.save(field));
    }

    @DeleteMapping("/fields/{fieldId}")
    public ResponseEntity<?> deleteField(@PathVariable int fieldId, @RequestHeader int tenantId) {
        if (fieldRepository.findByTenantIdAndFieldId(tenantId, fieldId).isPresent()) {
            fieldRepository.deleteByTenantIdAndFieldId(tenantId, fieldId);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
