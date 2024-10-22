package com.killahar.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
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
}
