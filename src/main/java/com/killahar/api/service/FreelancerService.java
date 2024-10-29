package com.killahar.api.service;

import com.killahar.api.entity.Freelancer;
import com.killahar.api.entity.Project;
import com.killahar.api.entity.Skill;
import com.killahar.api.entity.status.ProjectStatus;
import com.killahar.api.repository.FreelancerRepository;
import com.killahar.api.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FreelancerService {

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    public Freelancer addFreelancer(Freelancer freelancer) {
        return freelancerRepository.save(freelancer);
    }

    @Transactional
    public Freelancer assignFreelancerToProject(UUID freelancerId, UUID projectId) {
        Freelancer freelancer = freelancerRepository.findById(freelancerId)
                .orElseThrow(() -> new IllegalArgumentException("Фрилансер не найден с id: " + freelancerId));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Проект не найден с id: " + projectId));

        // Добавляем фрилансера в проект и обновляем связи
        project.getActiveFreelancers().add(freelancer);
        freelancer.getProjects().add(project);

        projectRepository.save(project); // Обновляем проект
        return freelancerRepository.save(freelancer); // Обновляем фрилансера
    }

    public List<Freelancer> getFreelancersBySkill(Skill skill) {
        return freelancerRepository.findAll().stream()
                .filter(f -> f.getSkills().contains(skill))
                .collect(Collectors.toList());
    }

    public List<Freelancer> getAvailableFreelancers() {
        return freelancerRepository.findAll().stream()
                .filter(freelancer -> freelancer.getProjects().isEmpty() || freelancer.getProjects().stream()
                        .allMatch(project -> project.getStatus() == ProjectStatus.COMPLETED))
                .collect(Collectors.toList());
    }

    public Set<Skill> getFreelancerSkills(UUID freelancerId) {
        Freelancer freelancer = freelancerRepository.findById(freelancerId)
                .orElseThrow(() -> new IllegalArgumentException("Фрилансер не найден с id: " + freelancerId));
        return freelancer.getSkills();
    }

    public boolean checkIfFreelancerHasSkill(UUID freelancerId, Skill skill) {
        Freelancer freelancer = freelancerRepository.findById(freelancerId)
                .orElseThrow(() -> new IllegalArgumentException("Фрилансер не найден с id: " + freelancerId));
        return freelancer.getSkills().contains(skill);
    }
}
