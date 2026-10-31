package ru.rutmiit.basketball.repository;

import ru.rutmiit.basketball.models.entities.Team;

import java.util.List;

public interface TeamCatalog {
    List<Team> findAll();

    void addTeam(Team team);

    void replaceTeam(String originalName, Team team);

    void removeTeam(Team team);

    boolean existByName(String name);
}
