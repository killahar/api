package com.killahar.api.repository;

import com.killahar.api.entity.Project;
import com.killahar.api.entity.status.ProjectStatus;
import com.killahar.api.entity.TeamLead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    List<Project> findByTeamlead(TeamLead teamlead);
    List<Project> findByStatus(ProjectStatus status);
}