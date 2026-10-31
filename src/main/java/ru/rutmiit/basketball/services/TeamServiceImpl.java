package ru.rutmiit.basketball.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.basketball.dto.AddTeamDto;
import ru.rutmiit.basketball.dto.TeamSummary;
import ru.rutmiit.basketball.models.entities.Team;
import ru.rutmiit.basketball.repositories.TeamRepository;
import ru.rutmiit.basketball.services.exceptions.TeamAlreadyExistsException;
import ru.rutmiit.basketball.services.exceptions.TeamNotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<ru.rutmiit.basketball.dto.TeamSummary> allTeams() {
        return teamRepository.findAllByOrderByNameAsc().stream().map(this::toSummary).toList();
    }

    @Override
    public TeamSummary teamDetails(String teamName) {
        return toSummary(findTeam(teamName));
    }

    @Override
    @Transactional
    public void addTeam(AddTeamDto form) {
        String name = form.getName().trim();
        if (teamRepository.existsByNameIgnoreCase(name)) {
            throw new TeamAlreadyExistsException();
        }
        Team team = new Team();
        copyFields(form, team);
        teamRepository.saveAndFlush(team);
    }

    @Override
    @Transactional
    public void updateTeam(String originalName, AddTeamDto form) {
        Team team = findTeam(originalName);
        String newName = form.getName().trim();
        if (!team.getName().equalsIgnoreCase(newName) && teamRepository.existsByNameIgnoreCase(newName)) {
            throw new TeamAlreadyExistsException();
        }
        copyFields(form, team);
        teamRepository.flush();
    }

    @Override
    @Transactional
    public void removeTeam(String teamName) {
        teamRepository.delete(findTeam(teamName));
        teamRepository.flush();
    }

    private Team findTeam(String name) {
        return teamRepository.findByName(name)
                .orElseThrow(() -> new TeamNotFoundException(name));
    }

    private void copyFields(AddTeamDto source, Team target) {
        target.setName(source.getName().trim());
        target.setCity(source.getCity().trim());
        target.setDescription(source.getDescription().trim());
        target.setFoundedYear(source.getFoundedYear());
    }

    private TeamSummary toSummary(Team team) {
        return new TeamSummary(team.getName(), team.getCity(),
                team.getDescription(), team.getFoundedYear());
    }
}
