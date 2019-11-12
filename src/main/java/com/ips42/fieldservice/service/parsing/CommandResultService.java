package com.ips42.fieldservice.service.parsing;

import com.ips42.fieldservice.entity.Measurement;
import com.ips42.fieldservice.entity.MeasurementFile;
import com.ips42.fieldservice.repository.MeasurementFileRepository;
import com.ips42.fieldservice.repository.MeasurementRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
class CommandResultService {

    private MeasurementRepository measurementRepository;
    private MeasurementFileRepository measurementFileRepository;

    public CommandResultService(MeasurementRepository measurementRepository,
                                MeasurementFileRepository measurementFileRepository) {
        this.measurementRepository = measurementRepository;
        this.measurementFileRepository = measurementFileRepository;
    }

    @Transactional
    public void addMeasurementsAndCommitParsing(ParseCommand command, List<Measurement> measurements) {
        measurementRepository.saveAll(measurements);

        MeasurementFile measurementFile = command.getMeasurementFile();
        measurementFile.setProcessed(true);
        measurementFileRepository.save(measurementFile);
    }
}
