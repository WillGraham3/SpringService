package ru.ilevda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import ru.ilevda.interfaces.FileService;

@Configuration
@ConfigurationProperties(prefix = "scheduledservice")
@Controller
public class ScheduledService {

    private String directory;
    private FileService fileService;

    @Autowired
    public ScheduledService(FileService fileService) {
        this.fileService = fileService;
    }

    @Scheduled(cron = "0 0/1 * * * * ")
    public void processUsersData() {
        fileService.service(directory);

    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
}
