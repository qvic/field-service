package com.ips42.fieldservice.service.parsing;

import com.ips42.fieldservice.entity.Measurement;
import com.ips42.fieldservice.entity.MeasurementFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

@Service
class CommandExecutorService {

    private static final Logger log = LoggerFactory.getLogger(CommandExecutorService.class);

    private CommandResultService commandResultService;

    public CommandExecutorService(CommandResultService commandResultService) {
        this.commandResultService = commandResultService;
    }

    @Async
    public void executeCommands(BlockingQueue<ParseCommand> queue, Set<MeasurementFile> queueFileSet) {
        while (true) {
            ParseCommand command;
            try {
                command = queue.take();
            } catch (InterruptedException e) {
                return;
            }

            List<Measurement> result = command.execute();
            log.debug("Executed command " + command + ". Result: " + result);
            commandResultService.addMeasurementsAndCommitParsing(command, result);
            queueFileSet.remove(command.getMeasurementFile());
        }
    }
}
