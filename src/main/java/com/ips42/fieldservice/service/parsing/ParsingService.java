package com.ips42.fieldservice.service.parsing;

import com.ips42.fieldservice.entity.MeasurementFile;
import com.ips42.fieldservice.repository.MeasurementFileRepository;
import com.ips42.fieldservice.util.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class ParsingService {

    private static final Logger log = LoggerFactory.getLogger(ParsingService.class);

    private MeasurementFileRepository measurementFileRepository;
    private CommandExecutorService commandExecutorService;

    private Parser parser;
    private BlockingQueue<ParseCommand> queue;
    private Set<MeasurementFile> fileSet;

    public ParsingService(MeasurementFileRepository measurementFileRepository,
                          CommandExecutorService commandExecutorService,
                          Parser parser) {
        this.measurementFileRepository = measurementFileRepository;
        this.parser = parser;
        this.commandExecutorService = commandExecutorService;
        this.queue = new LinkedBlockingQueue<>();
        this.fileSet = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    @Scheduled(fixedRate = 1000)
    private void refreshQueue() throws InterruptedException {
        log.info("Refreshing...");
        List<MeasurementFile> notProcessedFiles = measurementFileRepository.findAllByProcessedFalse();

        for (MeasurementFile file : notProcessedFiles) {

            if (!fileSet.contains(file)) {
                queue.put(new ParseCommand(file, parser));
                fileSet.add(file);
                log.info("Put file " + file);
            }
        }
    }

    @PostConstruct
    public void executeCommands() {
        commandExecutorService.executeCommands(queue, fileSet);
    }
}
