package com.ips42.fieldservice.repository;

import com.ips42.fieldservice.entity.Measurement;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeasurementRepository extends PagingAndSortingRepository<Measurement, Integer> {

    Optional<Measurement> findByTenantIdAndMeasureId(int tenantId, int measureId);

    Iterable<Measurement> findByTenantId(int tenantId);

    Iterable<Measurement> findByTenantIdAndDeviceId(int tenantId, int deviceId);

    // TODO fieldId is foreign key to field.id. Join tables
    Iterable<Measurement> findByTenantIdAndFieldId(int tenantId, int fieldId);

    void deleteByTenantIdAndMeasureId(int tenantId, int measureId);
}
