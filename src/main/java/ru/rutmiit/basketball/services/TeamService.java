package ru.rutmiit.basketball.services;

import ru.rutmiit.basketball.dto.AddTeamDto;
import ru.rutmiit.basketball.dto.TeamSummary;

import java.util.List;

public interface TeamService {

    List<TeamSummary> allTeams();

    TeamSummary teamDetails(String teamName);

    void addTeam(AddTeamDto dto);

    void updateTeam(String originalName, AddTeamDto dto);

    void removeTeam(String teamName);
}
