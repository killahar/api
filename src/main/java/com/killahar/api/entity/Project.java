package com.killahar.api.entity;

import com.killahar.api.entity.status.ProjectStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Data
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID projectId;

    @Column(nullable = false)
    private String projectName;

    @Lob
    @Column(nullable = false)
    private String projectDescription;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "teamlead_id", nullable = false)
    private TeamLead teamlead;

    @ManyToMany
    @JoinTable(
            name = "project_freelancers",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "freelancer_id")
    )
    private Set<Freelancer> activeFreelancers;

    private Double budget;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @ManyToMany
    @JoinTable(
            name = "project_required_skills",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> requiredSkills;


    // Метод для получения всех активных фрилансеров
    public Set<Freelancer> getActiveFreelancers() {
        return activeFreelancers; // Возвращаем множество активных фрилансеров
    }
}

