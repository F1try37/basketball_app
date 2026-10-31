package ru.rutmiit.basketball.repository;

import org.springframework.stereotype.Repository;
import ru.rutmiit.basketball.models.entities.Team;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryTeamCatalog implements TeamCatalog {

    private final List<Team> teams = new ArrayList<>();

    @Override
    public List<Team> findAll() {
        return teams;
    }

    @Override
    public void addTeam(Team team) {
        teams.add(team);
    }

    @Override
    public void replaceTeam(String originalName, Team team) {
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).name().equals(originalName)) {
                teams.set(i, team);
                return;
            }
        }
    }

    @Override
    public void removeTeam(Team team) {
        teams.removeIf(t -> team.name().equals(t.name()));
    }

    @Override
    public boolean existByName(String name) {
        for (Team team : teams) {
            if (team.name().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
