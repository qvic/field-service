package com.ips42.fieldservice.service;

import com.ips42.fieldservice.entity.Measurement;
import com.ips42.fieldservice.repository.FieldRepository;
import com.ips42.fieldservice.repository.MeasurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final TenantService tenantService;
    private final MeasurementRepository measurementRepository;

    public List<Measurement> getAll() {
        return measurementRepository.findByTenantId(tenantService.getCurrentTenantId());
    }

    public Optional<Measurement> getById(String measurementId) {
        return measurementRepository.findByTenantIdAndMeasureId(tenantService.getCurrentTenantId(), measurementId);
    }

    public List<Measurement> getByFieldId(String fieldId) {
        return measurementRepository.findByTenantIdAndFieldId(tenantService.getCurrentTenantId(), fieldId);
    }

    public List<Measurement> getByDeviceId(String deviceId) {
        return measurementRepository.findByTenantIdAndDeviceId(tenantService.getCurrentTenantId(), deviceId);
    }

    public void deleteByMeasurementId(String measurementId) {
        measurementRepository.deleteByTenantIdAndMeasureId(tenantService.getCurrentTenantId(), measurementId);
    }
}
