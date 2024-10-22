package com.killahar.api.service;

import com.killahar.api.entity.Project;
import com.killahar.api.entity.Freelancer;
import com.killahar.api.entity.TeamLead;
import com.killahar.api.repository.ProjectRepository;
import com.killahar.api.repository.TeamLeadRepository; // Импортируем репозиторий
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TeamLeadRepository teamLeadRepository; // Объявляем репозиторий

    public Project createProject(Project project) {
        // Логика для создания проекта
        return projectRepository.save(project);
    }

    public List<Project> getProjectsByTeamlead(UUID teamleadId) {
        TeamLead teamlead = teamLeadRepository.findById(teamleadId)
                .orElseThrow(() -> new IllegalArgumentException("Тимлид не найден с id: " + teamleadId));
        return projectRepository.findByTeamlead(teamlead); // Исправлено на teamlead
    }

    public void assignFreelancerToProject(UUID projectId, Freelancer freelancer) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Проект не найден с id: " + projectId));

        // Проверяем, уже добавлен ли фрилансер в проект
        if (project.getActiveFreelancers().contains(freelancer)) {
            throw new IllegalArgumentException("Фрилансер уже назначен на этот проект.");
        }

        // Добавляем фрилансера к проекту
        project.getActiveFreelancers().add(freelancer);
        projectRepository.save(project);
    }

    public Set<Freelancer> getFreelancersByProject(UUID projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Проект не найден с id: " + projectId));
        return project.getActiveFreelancers();  // Возвращаем всех активных фрилансеров
    }

    // Прочие методы для работы с проектами
}
