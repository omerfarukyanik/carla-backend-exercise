package org.example.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.CompensationDTO;
import org.example.model.Compensation;
import org.example.repository.CompensationRepository;
import org.example.utils.DTOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CompensationService {

    private final Logger LOGGER = LoggerFactory.getLogger(CompensationService.class);

    private final CompensationRepository compensationRepository;

    @Autowired
    public CompensationService(CompensationRepository compensationRepository) {
        this.compensationRepository = compensationRepository;
    }

    public String getFilteredAndSortedCompensations(Map<String, String> params) {
        Compensation compensation = createExampleCompensation(params);
        Sort sort = createSort(params);

        List<Compensation> compensationList = compensationRepository.findAll(Example.of(compensation), sort);
        List<CompensationDTO> compensationDTOs = mapToDTO(compensationList);

        // If fields parameter is set in request use it to eliminate fields
        String fields = params.get("fields");
        if (fields != null && !fields.isEmpty()) {
            compensationDTOs = filterFields(compensationDTOs, fields);
        }

        // filter out all the entities with all null values
        compensationDTOs = compensationDTOs.stream()
                .filter(dto -> !DTOUtils.isAllFieldsNull(dto))
                .toList();


        return serializeToJSON(compensationDTOs);

    }


    // Create an example Entity for the query purposes fallbacks to null values if parameter didn't set
    private Compensation createExampleCompensation(Map<String, String> filters) {
        // Create a new Compensation entity and set its fields based on the filter criteria
        Compensation example = new Compensation();

        example.setId(filters.getOrDefault("id", null) == null ? null : Long.parseLong(filters.get("id")));
        example.setTimestamp(filters.getOrDefault("timestamp", null));
        example.setAgeRange(filters.getOrDefault("ageRange", null));
        example.setIndustry(filters.getOrDefault("industry", null));
        example.setJobTitle(filters.getOrDefault("jobTitle", null));
        example.setAnnualSalary(filters.getOrDefault("annualSalary", null));
        example.setCurrency(filters.getOrDefault("currency", null));
        example.setLocation(filters.getOrDefault("location", null));
        example.setExperienceYears(filters.getOrDefault("experienceYears", null));
        example.setJobTitleContext(filters.getOrDefault("jobTitleContext", null));
        example.setOtherCurrency(filters.getOrDefault("otherCurrency", null));
        return example;
    }

    // Example usage /compensation_data?sort=field1:asc,field2:desc
    private Sort createSort(Map<String, String> filters) {
        // Create sorting options based on the provided sort parameter
        String sortParam = filters.get("sort");
        if (sortParam != null && !sortParam.isEmpty()) {
            String[] sortFields = sortParam.split(",");
            List<Order> orders = new ArrayList<>();
            for (String sortField : sortFields) {
                String[] parts = sortField.split(":");
                String fieldName = parts[0];
                Sort.Direction direction = Sort.Direction.ASC;
                if (parts.length > 1) {
                    direction = Sort.Direction.fromString(parts[1]);
                }
                orders.add(new Order(direction, fieldName));
            }
            return Sort.by(orders);
        }
        return Sort.unsorted();
    }


    // Create DTO out of the Entity List
    private List<CompensationDTO> mapToDTO(List<Compensation> compensationDataList) {
        List<CompensationDTO> dtoList = new ArrayList<>();
        for (Compensation compensation : compensationDataList) {
            CompensationDTO dto = new CompensationDTO(compensation);
            dtoList.add(dto);
        }
        return dtoList;
    }

    private String serializeToJSON(List<CompensationDTO> dtoList) {
        try {
            // Serialize the list of DTOs into JSON format using Jackson ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Exclude NULL fields
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return objectMapper.writeValueAsString(dtoList);
        } catch (Exception e) {
            LOGGER.error("Failed to serialize compensation list", e);
            return null;
        }
    }

    private List<CompensationDTO> filterFields(List<CompensationDTO> dtoList, String fields) {
        // iterate over the fields of each dto and set to real value if field name included in the fields query parameter
        // or set to null

        List<CompensationDTO> filteredList = new ArrayList<>();
        for (CompensationDTO dto : dtoList) {
            CompensationDTO filteredDTO = new CompensationDTO();
            String[] fieldNames = fields.split(",");
            for (String fieldName : fieldNames) {
                switch (fieldName) {
                    case "id":
                        filteredDTO.setId(dto.getId());
                        break;
                    case "timestamp":
                        filteredDTO.setTimestamp(dto.getTimestamp());
                        break;
                    case "ageRange":
                        filteredDTO.setAgeRange(dto.getAgeRange());
                        break;
                    case "industry":
                        filteredDTO.setIndustry(dto.getIndustry());
                        break;
                    case "jobTitle":
                        filteredDTO.setJobTitle(dto.getJobTitle());
                        break;
                    case "annualSalary":
                        filteredDTO.setAnnualSalary(dto.getAnnualSalary());
                        break;
                    case "currency":
                        filteredDTO.setCurrency(dto.getCurrency());
                        break;
                    case "location":
                        filteredDTO.setLocation(dto.getLocation());
                        break;
                    case "experienceYears":
                        filteredDTO.setExperienceYears(dto.getExperienceYears());
                        break;
                    case "jobTitleContext":
                        filteredDTO.setJobTitleContext(dto.getJobTitleContext());
                        break;
                    case "otherCurrency":
                        filteredDTO.setOtherCurrency(dto.getOtherCurrency());
                        break;
                    // Add additional cases for other fields as needed
                }
            }
            filteredList.add(filteredDTO);
        }
        return filteredList;
    }
}
