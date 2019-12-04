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
import java.util.concurrent.atomic.AtomicInteger;

class ParseCommand {

    private static final Logger log = LoggerFactory.getLogger(ParseCommand.class);
    private static AtomicInteger dummyMeasureId = new AtomicInteger(0);

    @Getter
    private MeasurementFile measurementFile;

    private Parser parser;

    ParseCommand(MeasurementFile measurementFile, Parser parser) {
        this.measurementFile = Objects.requireNonNull(measurementFile);
        this.parser = parser;
    }

    List<Measurement> execute() {
        return parser.parseMeasurements(measurementFile.getContent());
    }
}
