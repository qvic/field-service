package com.ips42.fieldservice.repository;

import com.ips42.fieldservice.entity.Measurement;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeasurementRepository extends PagingAndSortingRepository<Measurement, Integer> {

    Optional<Measurement> findByTenantIdAndMeasureId(String tenantId, String measureId);

    List<Measurement> findByTenantId(String tenantId);

    List<Measurement> findByTenantIdAndDeviceId(String tenantId, String deviceId);

    List<Measurement> findByTenantIdAndFieldId(String tenantId, String fieldId);

    void deleteByTenantIdAndMeasureId(String tenantId, String measureId);
}
