package com.civa.challenge.civatours.platform.bus.infrastructure.persistence.jpa.repositories;

import com.civa.challenge.civatours.platform.bus.domain.model.entities.BusStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusStatusRepository extends JpaRepository<BusStatus, String> {
    Optional<BusStatus> findByStatusName(String statusName);
}
