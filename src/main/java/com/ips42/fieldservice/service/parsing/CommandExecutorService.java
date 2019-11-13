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

    private CommandCompletionService commandCompletionService;

    public CommandExecutorService(CommandCompletionService commandCompletionService) {
        this.commandCompletionService = commandCompletionService;
    }

    @Async
    public void executeCommands(BlockingQueue<ParseCommand> queue, Set<MeasurementFile> fileSet) {
        while (true) {
            ParseCommand command;
            try {
                command = queue.take();
            } catch (InterruptedException e) {
                return;
            }

            List<Measurement> result = command.execute();
            commandCompletionService.addMeasurementsAndCommitParsing(command, result);
            fileSet.remove(command.getMeasurementFile());
        }
    }
}
