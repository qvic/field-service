package com.ips42.fieldservice.service;

import com.ips42.fieldservice.dto.FieldDto;
import com.ips42.fieldservice.entity.Field;
import com.ips42.fieldservice.repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FieldService {

    private final TenantService tenantService;
    private final FieldRepository fieldRepository;

    public List<FieldDto> getAll() {
        return fieldRepository.findByTenantId(tenantService.getCurrentTenantId())
                .stream()
                .map(Field::toFieldDto)
                .collect(Collectors.toList());
    }

    public Optional<FieldDto> getByFieldId(String fieldId) {
        return fieldRepository.findByTenantIdAndFieldId(tenantService.getCurrentTenantId(), fieldId)
                .map(Field::toFieldDto);
    }

    public Optional<FieldDto> updateField(String fieldId, FieldDto dto) {
        String tenantId = tenantService.getCurrentTenantId();
        Optional<Field> fieldById = fieldRepository.findByTenantIdAndFieldId(tenantId, fieldId);

        if (fieldById.isEmpty()) {
            return Optional.empty();
        }

        Field updatedField = dto.toField(tenantId, "user", Instant.now());
        updatedField.setId(fieldById.get().getId());
        fieldRepository.save(updatedField);

        return Optional.of(updatedField.toFieldDto());
    }

    public void saveField(FieldDto dto) {
        fieldRepository.save(dto.toField(tenantService.getCurrentTenantId(), "user", Instant.now()));
    }

    public void deleteField(String fieldId) {
        fieldRepository.deleteByTenantIdAndFieldId(tenantService.getCurrentTenantId(), fieldId);
    }
}
