package org.example.component;

import org.example.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CSVInitializer implements CommandLineRunner {

    private final Environment environment;
    private final CSVService csvService;

    @Autowired
    public CSVInitializer(Environment environment, CSVService csvService) {
        this.environment = environment;
        this.csvService = csvService;
    }

    // Runs on application startup
    @Override
    public void run(String... args) {
        String filePath = environment.getProperty("db.file.path");
        csvService.parseAndSaveToDb_CSV(filePath);
    }
}
