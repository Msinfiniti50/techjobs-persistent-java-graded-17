package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {
    @Size(max =50, message = "Description must be between 3 and 50 characters.")
    public String description;

    @ManyToMany(mappedBy = "skills")
    private List<Job> jobs = new ArrayList<>();

    public Skill(){
    }
@Size(max = 50, message = "Description must be between 3 and 50 characters.")
    public Skill(String getDescription) {
        super();
        this.description = getDescription;

    }
    public List<Job> getJobs() {
        return jobs;
    }
    public void setJobs(List<Job> jobs) {
        this.jobs =jobs;
    }

    public String getDescription() {
        return description;

    }

    public void setDescription(String description) {
        this.description = description;

    }
}

