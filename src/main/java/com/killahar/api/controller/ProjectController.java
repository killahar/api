package com.killahar.api.controller;

import com.killahar.api.entity.Freelancer;
import com.killahar.api.entity.Project;
import com.killahar.api.entity.TeamLead;
import com.killahar.api.service.ProjectService;
import com.killahar.api.repository.TeamLeadRepository; // Убедитесь, что этот импорт присутствует
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
@Validated
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TeamLeadRepository teamLeadRepository; // Объявление репозитория

    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@Valid @RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @GetMapping("/teamlead/{teamleadId}")
    public ResponseEntity<List<Project>> getProjectsByTeamlead(@PathVariable UUID teamleadId) {
        TeamLead teamlead = teamLeadRepository.findById(teamleadId)
                .orElseThrow(() -> new IllegalArgumentException("Тимлид не найден с id: " + teamleadId));
        List<Project> projects = projectService.getProjectsByTeamlead(teamleadId);
        if (projects.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(projects);
    }

    // Новый метод для получения всех фрилансеров по проекту
    @GetMapping("/{projectId}/freelancers")
    public ResponseEntity<Set<Freelancer>> getFreelancersByProject(@PathVariable UUID projectId) {
        Set<Freelancer> freelancers = projectService.getFreelancersByProject(projectId);
        if (freelancers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(freelancers);
    }
}
