package com.killahar.api.service;

import com.killahar.api.entity.Project;
import com.killahar.api.entity.Freelancer;
import com.killahar.api.entity.Skill;
import com.killahar.api.entity.TeamLead;
import com.killahar.api.repository.FreelancerRepository;
import com.killahar.api.repository.ProjectRepository;
import com.killahar.api.repository.TeamLeadRepository; // Импортируем репозиторий
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    private TeamLeadRepository teamLeadRepository; // Добавляем репозиторий TeamLead

    @Transactional
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getProjectsByTeamlead(UUID teamleadId) {
        // Получаем TeamLead из репозитория
        TeamLead teamLead = teamLeadRepository.findById(teamleadId)
                .orElseThrow(() -> new IllegalArgumentException("Тимлид не найден с id: " + teamleadId));

        // Используем найденного TeamLead для поиска проектов
        return projectRepository.findByTeamlead(teamLead);
    }

    public Set<Freelancer> getFreelancersByProject(UUID projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Проект не найден с id: " + projectId));
        return project.getActiveFreelancers();
    }

    public boolean checkSkillCoverage(Project project) {
        Set<Skill> requiredSkills = project.getRequiredSkills();
        Set<Skill> freelancerSkills = project.getActiveFreelancers().stream()
                .flatMap(freelancer -> freelancer.getSkills().stream())
                .collect(Collectors.toSet());

        return freelancerSkills.containsAll(requiredSkills);
    }
}



