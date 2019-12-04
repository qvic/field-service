package com.ips42.fieldservice.controller;

import com.ips42.fieldservice.dto.FieldDto;
import com.ips42.fieldservice.entity.Field;
import com.ips42.fieldservice.repository.FieldRepository;
import com.ips42.fieldservice.service.FieldService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@RestController
@RequiredArgsConstructor
public class FieldController {

    private final FieldService fieldService;

    @GetMapping("/fields")
    public List<FieldDto> getAll() {
        return fieldService.getAll();
    }

    @GetMapping("/fields/{fieldId}")
    public ResponseEntity<FieldDto> getByFieldId(@PathVariable String fieldId) {
        return ResponseEntity.of(fieldService.getByFieldId(fieldId));
    }

    @PostMapping(value = "/fields", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createField(@RequestBody FieldDto field) {
        fieldService.saveField(field);
    }

    @PutMapping("/fields/{fieldId}")
    public ResponseEntity<FieldDto> updateField(@PathVariable String fieldId, @RequestBody FieldDto field) {
        return ResponseEntity.of(fieldService.updateField(fieldId, field));
    }

    @DeleteMapping("/fields/{fieldId}")
    public void deleteField(@PathVariable String fieldId) {
        fieldService.deleteField(fieldId);
    }
}
