package com.hrsystem.hrsystem.entity;

import javax.persistence.*;

@Entity
public class EmployeeExperties {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String job;
    private String jobdescription;
    private String experties;

    public EmployeeExperties() {
    }

    public EmployeeExperties(String job, String jobdescription, String experties) {
        this.job = job;
        this.jobdescription = jobdescription;
        this.experties = experties;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJopTitle() {
        return job;
    }

    public void setJopTitle(String jopTitle) {
        this.job = jopTitle;
    }

    public String getJobdescription() {
        return jobdescription;
    }

    public void setJobdescription(String jobdescription) {
        this.jobdescription = jobdescription;
    }

    public String getExperties() {
        return experties;
    }

    public void setExperties(String experties) {
        this.experties = experties;
    }
}
