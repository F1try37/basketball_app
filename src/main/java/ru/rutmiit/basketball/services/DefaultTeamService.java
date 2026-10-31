package ru.rutmiit.basketball.services;

import org.springframework.stereotype.Service;
import ru.rutmiit.basketball.repository.TeamCatalog;
import ru.rutmiit.basketball.dto.AddTeamDto;
import ru.rutmiit.basketball.models.entities.Team;
import ru.rutmiit.basketball.services.exceptions.TeamNotFoundException;

import java.util.List;

@Service
public class DefaultTeamService implements TeamService {

    private final TeamCatalog teamCatalog;

    public DefaultTeamService(TeamCatalog teamCatalog) {
        this.teamCatalog = teamCatalog;
    }

    @Override
    public List<Team> allTeams() {
        return teamCatalog.findAll();
    }

    @Override
    public Team teamDetails(String teamName) {
        return teamCatalog.findAll().stream()
                .filter(team -> team.name().equals(teamName))
                .findFirst()
                .orElseThrow(() -> new TeamNotFoundException(teamName));
    }

    private Team toTeam(String normalizedName, AddTeamDto dto) {
        return new Team(normalizedName, dto.getCity().trim(), dto.getDescription().trim(), dto.getFoundedYear());
    }

    @Override
    public void addTeam(AddTeamDto dto) {
        String normalizedName = dto.getName().trim();
        if (teamCatalog.existByName(normalizedName)) {
            throw new IllegalArgumentException("Команда уже существует");
        }
        teamCatalog.addTeam(toTeam(normalizedName, dto));
    }

    @Override
    public void updateTeam(String originalName, AddTeamDto dto) {
        teamCatalog.replaceTeam(originalName, toTeam(dto.getName().trim(), dto));
    }

    @Override
    public void removeTeam(String teamName) {
        Team team = teamDetails(teamName);
        teamCatalog.removeTeam(team);
    }
}
