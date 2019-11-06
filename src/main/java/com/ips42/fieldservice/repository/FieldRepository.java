package com.ips42.fieldservice.repository;

import com.ips42.fieldservice.entity.Field;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FieldRepository extends PagingAndSortingRepository<Field, Integer> {

    Optional<Field> findByTenantIdAndFieldId(int tenantId, int fieldId);

    Iterable<Field> findByTenantId(int tenantId);

    void deleteByTenantIdAndFieldId(int tenantId, int fieldId);
}
