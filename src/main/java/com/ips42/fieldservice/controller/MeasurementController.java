package com.ips42.fieldservice.controller;

import com.ips42.fieldservice.entity.Measurement;
import com.ips42.fieldservice.entity.MeasurementFile;
import com.ips42.fieldservice.exceptions.FileLoadingException;
import com.ips42.fieldservice.repository.MeasurementFileRepository;
import com.ips42.fieldservice.repository.MeasurementRepository;
import com.ips42.fieldservice.service.MeasurementService;
import com.ips42.fieldservice.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MeasurementController {

    private final MeasurementService measurementService;
    private final StorageService storageService;

    @GetMapping("/measurements")
    public List<Measurement> getAll() {
        return measurementService.getAll();
    }

    @GetMapping("fields/{fieldId}/measurements")
    public List<Measurement> getByFieldId(@PathVariable String fieldId) {
        return measurementService.getByFieldId(fieldId);
    }

    @GetMapping("devices/{deviceId}/measurements")
    public List<Measurement> getByDeviceId(@PathVariable String deviceId) {
        return measurementService.getByDeviceId(deviceId);
    }

    @GetMapping("/measurements/{measurementId}")
    public ResponseEntity<Measurement> getByMeasurementId(@PathVariable String measurementId) {
        return ResponseEntity.of(measurementService.getById(measurementId));
    }

    @DeleteMapping("/measurements/{measurementId}")
    public void deleteField(@PathVariable String measurementId) {
        measurementService.deleteByMeasurementId(measurementId);
    }

    @PostMapping("/measurements")
    public void uploadMeasurementFile(@RequestParam(value = "file") MultipartFile file) {
        try {
            storageService.storeFile(file);
        } catch (IOException e) {
            throw new FileLoadingException();
        }
    }
}
