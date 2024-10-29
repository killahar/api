package com.killahar.api.entity;

import com.killahar.api.entity.status.FreelancerLevel;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
public class Freelancer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID freelancerId;

    @Column(nullable = false)
    private String name;

    private String portfolio;

    @ManyToMany
    @JoinTable(
            name = "freelancer_skills",
            joinColumns = @JoinColumn(name = "freelancer_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills;

    @Enumerated(EnumType.STRING)
    private FreelancerLevel freelancerLevel;

    private String photo;
    private Double hourlyRate;

    @ManyToMany
    @JoinTable(
            name = "freelancer_projects",  // Связь с проектами, в которых участвует фрилансер
            joinColumns = @JoinColumn(name = "freelancer_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> projects;

    @Column(unique = true, nullable = false)
    private String email;

    // Геттеры и сеттеры

    public UUID getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(UUID freelancerId) {
        this.freelancerId = freelancerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public FreelancerLevel getFreelancerLevel() {
        return freelancerLevel;
    }

    public void setFreelancerLevel(FreelancerLevel freelancerLevel) {
        this.freelancerLevel = freelancerLevel;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
