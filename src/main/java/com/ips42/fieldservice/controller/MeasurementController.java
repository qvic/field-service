package com.ips42.fieldservice.controller;

import com.ips42.fieldservice.entity.Measurement;
import com.ips42.fieldservice.entity.MeasurementFile;
import com.ips42.fieldservice.repository.MeasurementFileRepository;
import com.ips42.fieldservice.repository.MeasurementRepository;
import com.ips42.fieldservice.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class MeasurementController {

    private static final Logger log = LoggerFactory.getLogger(MeasurementController.class);

    private MeasurementRepository measurementRepository;
    private StorageService storageService;

    public MeasurementController(MeasurementRepository measurementRepository,
                                 StorageService storageService) {
        this.measurementRepository = measurementRepository;
        this.storageService = storageService;
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
        log.info("Got file " + file);
        try {
            storageService.storeFile(file);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
