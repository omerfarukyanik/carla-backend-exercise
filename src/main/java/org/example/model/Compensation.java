package org.example.model;

import jakarta.persistence.*;

@Entity
public class Compensation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String timestamp;
    private String ageRange;
    private String industry;
    private String jobTitle;
    private String annualSalary;
    private String currency;
    private String location;
    private String experienceYears;
    @Column(length = 1000)
    private String jobTitleContext;
    private String otherCurrency;

    // getters and setters
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
        if (jobTitleContext == null || jobTitleContext.isEmpty()) {
            this.jobTitleContext = jobTitleContext;
        } else { // limit the char length to 1000 chars and clip the rest of it
            this.jobTitleContext = jobTitleContext.substring(0, Math.min(jobTitleContext.length(), 1000));
        }
    }

    public String getOtherCurrency() {
        return otherCurrency;
    }

    public void setOtherCurrency(String otherCurrency) {
        this.otherCurrency = otherCurrency;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", ageRange='" + ageRange + '\'' +
                ", industry='" + industry + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", annualSalary=" + annualSalary +
                ", currency='" + currency + '\'' +
                ", location='" + location + '\'' +
                ", experienceYears='" + experienceYears + '\'' +
                ", jobTitleContext='" + jobTitleContext + '\'' +
                ", otherCurrency='" + otherCurrency + '\'' +
                '}';
    }
}
