package com.ips42.fieldservice.service;

import com.ips42.fieldservice.entity.MeasurementFile;
import com.ips42.fieldservice.repository.MeasurementFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class StorageService {

    private MeasurementFileRepository measurementFileRepository;

    public StorageService(MeasurementFileRepository measurementFileRepository) {
        this.measurementFileRepository = measurementFileRepository;
    }

    public void storeFile(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        MeasurementFile measurementFile = new MeasurementFile();
        measurementFile.setProcessed(false);
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Failed to store empty file " + filename);
        }
        try (InputStream inputStream = file.getInputStream()) {
            measurementFile.setContent(inputStreamToString(inputStream));
        }
        measurementFileRepository.save(measurementFile);
    }

    private String inputStreamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString(StandardCharsets.UTF_8);
    }
}
