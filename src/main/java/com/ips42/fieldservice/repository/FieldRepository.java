package com.ips42.fieldservice.repository;

import com.ips42.fieldservice.entity.Field;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FieldRepository extends PagingAndSortingRepository<Field, Integer> {

    Optional<Field> findByTenantIdAndFieldId(String tenantId, String fieldId);

    List<Field> findByTenantId(String tenantId);

    void deleteByTenantIdAndFieldId(String tenantId, String fieldId);
}
