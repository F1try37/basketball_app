package ru.rutmiit.basketball.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rutmiit.basketball.models.entities.Team;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {
    Optional<Team> findByName(String name);

    boolean existsByNameIgnoreCase(String name);

    List<Team> findAllByOrderByNameAsc();
}
