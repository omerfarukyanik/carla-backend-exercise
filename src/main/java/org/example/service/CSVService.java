package org.example.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.model.Compensation;
import org.example.repository.CompensationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVService.class);

    private final CompensationRepository compensationRepository;
    private final ResourceLoader resourceLoader;


    @Autowired
    public CSVService(CompensationRepository compensationRepository, ResourceLoader resourceLoader) {
        this.compensationRepository = compensationRepository;
        this.resourceLoader = resourceLoader;
    }

    public void parseAndSaveToDb_CSV(String ResourceFilePath) {
        try {
            // To get the file located under the resources directory we must use ResourceLoader
            Resource resource = resourceLoader.getResource(ResourceFilePath);

            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build();

            // Set these here to out close when the block finishes
            try (InputStream inputStream = resource.getInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                 CSVParser csvParser = new CSVParser(reader, csvFormat)
            ) {

                List<Compensation> compensations = new ArrayList<>();

                // loop through each row
                for (CSVRecord csvRecord : csvParser) {
                    Compensation compensation = new Compensation();
                    compensation.setTimestamp(csvRecord.get("Timestamp"));
                    compensation.setAgeRange(csvRecord.get("How old are you?"));
                    compensation.setIndustry(csvRecord.get("What industry do you work in?"));
                    compensation.setJobTitle(csvRecord.get("Job title"));
                    compensation.setAnnualSalary(csvRecord.get("What is your annual salary?"));
                    compensation.setCurrency(csvRecord.get("Please indicate the currency"));
                    compensation.setLocation(csvRecord.get("Where are you located? (City/state/country)"));
                    compensation.setExperienceYears(csvRecord.get("How many years of post-college professional work experience do you have?"));
                    compensation.setJobTitleContext(csvRecord.get("If your job title needs additional context, please clarify here:"));
                    compensation.setOtherCurrency(csvRecord.get("If \"Other,\" please indicate the currency here: "));

                    compensations.add(compensation);
                    LOGGER.debug("Created employee with id: {}", compensation.getId());
                }

                compensationRepository.saveAll(compensations);
                LOGGER.info("Saved {} employees.", compensations.size());
            }

        } catch (IOException e) {
            LOGGER.error("Error while parsing CSV file.", e);
        }
    }
}
