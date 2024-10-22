package com.killahar.api.repository;

import com.killahar.api.entity.TeamLead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeamLeadRepository extends JpaRepository<TeamLead, UUID> {
    // Здесь можно добавить дополнительные методы, если необходимо
}
