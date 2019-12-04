package com.ips42.fieldservice.util;

import com.ips42.fieldservice.entity.Measurement;
import com.ips42.fieldservice.service.TenantService;
import com.ips42.fieldservice.service.parsing.ParsingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Parser {

    private static final Logger log = LoggerFactory.getLogger(Parser.class);

    private final Pattern linePattern = Pattern.compile("\n");
    private final TenantService tenantService;

    public List<Measurement> parseMeasurements(String measurements) {
        log.info(measurements);

        List<Measurement> collect = linePattern.splitAsStream(measurements).map(s -> {
            String[] coordsAndMeasures = s.split(" ");
            Measurement m = new Measurement();
            m.setLatitude(Double.parseDouble(coordsAndMeasures[0]));
            m.setLongitude(Double.parseDouble(coordsAndMeasures[1]));

            m.setMeasures(Arrays.stream(coordsAndMeasures).skip(2)
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new));

            m.setDate(Instant.now());
            m.setTenantId(tenantService.getCurrentTenantId());
            m.setMeasureId(String.valueOf(UUID.randomUUID()));

            return m;
        }).collect(Collectors.toList());

        log.info(collect.toString());

        return collect;
    }
}
