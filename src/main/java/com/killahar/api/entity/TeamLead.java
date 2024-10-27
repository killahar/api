package com.killahar.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
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
}
