package com.civa.challenge.civatours.platform.iam.infrastructure.persistance.jpa.repositories;

import com.civa.challenge.civatours.platform.iam.domain.model.aggregates.User;
import com.civa.challenge.civatours.platform.iam.domain.model.valueobjects.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserById(UUID id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findAllByRole_Name(Roles role);
}
