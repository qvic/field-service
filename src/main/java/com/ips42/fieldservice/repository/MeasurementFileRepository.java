package com.ips42.fieldservice.repository;

import com.ips42.fieldservice.entity.MeasurementFile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MeasurementFileRepository extends CrudRepository<MeasurementFile, Integer> {

    List<MeasurementFile> findAllByProcessedFalse();
}
