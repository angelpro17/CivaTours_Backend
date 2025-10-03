package com.civa.challenge.civatours.platform.bus.infrastructure.persistence.jpa.repositories;

import com.civa.challenge.civatours.platform.bus.domain.model.aggregates.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BusRepository extends JpaRepository<Bus, UUID> {
    Optional<Bus> findByBusNumber(String busNumber);
    Optional<Bus> findByLicensePlate(String licensePlate);
}
