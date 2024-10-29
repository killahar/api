package com.killahar.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity
public class TeamLead {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID teamleadId;

    @NotBlank(message = "Имя тимлида не должно быть пустым.")
    private String name;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Email не должен быть пустым.")
    @Email(message = "Некорректный формат email.")
    private String email;

    @OneToMany(mappedBy = "teamlead")
    private Set<Project> projects;

    public UUID getTeamleadId() {
        return teamleadId;
    }

    public void setTeamleadId(UUID teamleadId) {
        this.teamleadId = teamleadId;
    }

    public @NotBlank(message = "Имя тимлида не должно быть пустым.") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Имя тимлида не должно быть пустым.") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Email не должен быть пустым.") @Email(message = "Некорректный формат email.") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email не должен быть пустым.") @Email(message = "Некорректный формат email.") String email) {
        this.email = email;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
