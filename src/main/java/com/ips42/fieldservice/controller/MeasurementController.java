package com.ips42.fieldservice.controller;

import com.ips42.fieldservice.entity.Field;
import com.ips42.fieldservice.entity.Measurement;
import com.ips42.fieldservice.repository.MeasurementRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MeasurementController {

    private MeasurementRepository measurementRepository;

    public MeasurementController(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @GetMapping("/measurements")
    public ResponseEntity<Iterable<Measurement>> getAll(@RequestHeader int tenantId) {
        return ResponseEntity.ok(measurementRepository.findByTenantId(tenantId));
    }

    @GetMapping("fields/{fieldId}/measurements")
    public ResponseEntity<Iterable<Measurement>> getByFieldId(@PathVariable int fieldId, @RequestHeader int tenantId) {
        return ResponseEntity.ok(measurementRepository.findByTenantIdAndFieldId(tenantId, fieldId));
    }

    @GetMapping("devices/{deviceId}/measurements")
    public ResponseEntity<Iterable<Measurement>> getByDeviceId(@PathVariable int deviceId, @RequestHeader int tenantId) {
        return ResponseEntity.ok(measurementRepository.findByTenantIdAndDeviceId(tenantId, deviceId));
    }

    @DeleteMapping("/measurements/{measurementId}")
    public ResponseEntity<?> deleteField(@PathVariable int measurementId, @RequestHeader int tenantId) {
        if (measurementRepository.findByTenantIdAndMeasureId(tenantId, measurementId).isPresent()) {
            measurementRepository.deleteByTenantIdAndMeasureId(tenantId, measurementId);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/measurements")
    public ResponseEntity uploadMeasurementFile(@RequestParam(value = "file") MultipartFile file) {
        System.out.println("Storing file " + file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
