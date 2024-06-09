package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.model.Compensation;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CompensationDTO {

    private Long id;
    private String timestamp;
    private String ageRange;
    private String industry;
    private String jobTitle;
    private String annualSalary;
    private String currency;
    private String location;
    private String experienceYears;
    private String jobTitleContext;
    private String otherCurrency;

    public CompensationDTO(Compensation compensation) {
        this.id = compensation.getId();
        this.timestamp = compensation.getTimestamp();
        this.ageRange = compensation.getAgeRange();
        this.industry = compensation.getIndustry();
        this.jobTitle = compensation.getJobTitle();
        this.annualSalary = compensation.getAnnualSalary();
        this.currency = compensation.getCurrency();
        this.location = compensation.getLocation();
        this.experienceYears = compensation.getExperienceYears();
        this.jobTitleContext = compensation.getJobTitleContext();
        this.otherCurrency = compensation.getOtherCurrency();
    }

    public CompensationDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(String annualSalary) {
        this.annualSalary = annualSalary;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(String experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getJobTitleContext() {
        return jobTitleContext;
    }

    public void setJobTitleContext(String jobTitleContext) {
        this.jobTitleContext = jobTitleContext;
    }

    public String getOtherCurrency() {
        return otherCurrency;
    }

    public void setOtherCurrency(String otherCurrency) {
        this.otherCurrency = otherCurrency;
    }

    @Override
    public String toString() {
        return "CompensationDTO{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", ageRange='" + ageRange + '\'' +
                ", industry='" + industry + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", annualSalary='" + annualSalary + '\'' +
                ", currency='" + currency + '\'' +
                ", location='" + location + '\'' +
                ", experienceYears='" + experienceYears + '\'' +
                ", jobTitleContext='" + jobTitleContext + '\'' +
                ", otherCurrency='" + otherCurrency + '\'' +
                '}';
    }
}
