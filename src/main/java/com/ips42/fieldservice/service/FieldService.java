package com.ips42.fieldservice.service;

import com.ips42.fieldservice.dto.FieldDto;
import com.ips42.fieldservice.entity.Field;
import com.ips42.fieldservice.repository.FieldRepository;
import com.ips42.fieldservice.util.Parser;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FieldService {

    private static final Logger log = LoggerFactory.getLogger(FieldService.class);

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

    public Optional<FieldDto> saveField(FieldDto dto) {
        // todo auditing
        Field field = dto.toField(tenantService.getCurrentTenantId(), "user", Instant.now());

        Boolean disjointAndValidField = fieldRepository.isDisjointAndValidField(field.getGisPolygon().toString());

        if (disjointAndValidField) {
            Field saved = fieldRepository.save(field);
            return Optional.of(saved.toFieldDto());
        }

        return Optional.empty();
    }

    public void deleteField(String fieldId) {
        fieldRepository.deleteByTenantIdAndFieldId(tenantService.getCurrentTenantId(), fieldId);
    }
}
