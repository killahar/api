package com.killahar.api.entity;

import com.killahar.api.entity.status.SkillLevel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID skillId;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private SkillLevel skillLevel;

    @ManyToMany(mappedBy = "skills")
    private Set<Freelancer> freelancers;

    @ManyToMany
    @JoinTable(
            name = "skill_projects",  // Добавлена связь навыков с проектами
            joinColumns = @JoinColumn(name = "skill_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> projects;

    public UUID getSkillId() {
        return skillId;
    }

    public void setSkillId(UUID skillId) {
        this.skillId = skillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SkillLevel getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(SkillLevel skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Set<Freelancer> getFreelancers() {
        return freelancers;
    }

    public void setFreelancers(Set<Freelancer> freelancers) {
        this.freelancers = freelancers;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
