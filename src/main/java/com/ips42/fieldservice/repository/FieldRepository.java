package com.ips42.fieldservice.repository;

import com.ips42.fieldservice.entity.Field;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FieldRepository extends PagingAndSortingRepository<Field, Integer> {

    Optional<Field> findByTenantIdAndFieldId(int tenantId, int fieldId);

    Iterable<Field> findByTenantId(int tenantId);

    void deleteByTenantIdAndFieldId(int tenantId, int fieldId);

    @Query(nativeQuery = true,
            value = "SELECT ST_Area(cast(f.gis_polygon as geography)) FROM field f WHERE f.tenant_id = ?1 AND f.field_id = ?2")
    Double getAreaByFieldId(int tenantId, int fieldId);
}
