package com.killahar.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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

    // Метод для получения всех активных фрилансеров
    public Set<Freelancer> getActiveFreelancers() {
        return activeFreelancers; // Возвращаем множество активных фрилансеров
    }
}
