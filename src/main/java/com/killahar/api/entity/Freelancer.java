package com.killahar.api.entity;

import com.killahar.api.entity.status.FreelancerLevel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
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
}
