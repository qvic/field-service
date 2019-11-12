package com.ips42.fieldservice.service.parsing;

import com.ips42.fieldservice.entity.Measurement;
import com.ips42.fieldservice.entity.MeasurementFile;
import com.ips42.fieldservice.util.Parser;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

class ParseCommand {

    private static final Logger log = LoggerFactory.getLogger(ParseCommand.class);
    private static int dummyMeasureId = 0;

    @Getter
    private MeasurementFile measurementFile;

    private Parser parser;

    ParseCommand(MeasurementFile measurementFile, Parser parser) {
        this.measurementFile = Objects.requireNonNull(measurementFile);
        this.parser = parser;
    }

    List<Measurement> execute() {
//        return parser.parseMeasurements(Files.readString(Paths.get(file.getFilePath())));

        log.info("Started parsing file " + measurementFile);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Measurement dummy = new Measurement();
        dummy.setDate(Instant.now());
        dummy.setTenantId(1);
        dummy.setFieldId(1);
        dummy.setMeasureId(dummyMeasureId++);
        dummy.setMeasuresJson("{\"test\":\"test\"}");

        log.info("Finished parsing file " + measurementFile);

        return List.of(dummy);
    }
}
