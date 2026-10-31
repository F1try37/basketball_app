package ru.rutmiit.basketball.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rutmiit.basketball.dto.AddTeamDto;
import ru.rutmiit.basketball.dto.TeamSummary;
import ru.rutmiit.basketball.services.TeamService;
import ru.rutmiit.basketball.services.exceptions.TeamAlreadyExistsException;

@Controller
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public String allTeams(Model model) {
        model.addAttribute("teamInfos", teamService.allTeams());
        return "team-all";
    }

    @GetMapping("/{teamName}")
    public String teamDetails(
            @PathVariable String teamName,
            Model model
    ) {
        model.addAttribute("teamDetails", teamService.teamDetails(teamName));
        return "team-details";
    }

    @ModelAttribute("teamModel")
    public AddTeamDto teamForm() {
        return new AddTeamDto();
    }

    @GetMapping("/add")
    public String addTeamForm(Model model) {
        prepareCreateForm(model);
        return "team-add";
    }

    @PostMapping
    public String addTeam(
            @Valid @ModelAttribute("teamModel") AddTeamDto dto,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
            ) {

        if (bindingResult.hasErrors()) {
            prepareCreateForm(model);
            return "team-add";
        }

        try {
            teamService.addTeam(dto);
        } catch (TeamAlreadyExistsException exception) {
            bindingResult.rejectValue("name", "duplicate", exception.getMessage());
            prepareCreateForm(model);
            return "team-add";
        }

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Команда '" + dto.getName().trim() + "' успешно добавлена"
        );
        return "redirect:/teams";
    }

    @GetMapping("/{teamName}/edit")
    public String editTeamForm(
            @PathVariable String teamName,
            Model model
            ) {
        TeamSummary team = teamService.teamDetails(teamName);

        AddTeamDto form = new AddTeamDto();
        form.setName(team.name());
        form.setCity(team.city());
        form.setDescription(team.description());
        form.setFoundedYear(team.foundedYear());

        model.addAttribute("teamModel", form);
        prepareEditForm(model, teamName);
        return "team-add";
    }

    @PostMapping("/{teamName}")
    public String updateTeam(
            @PathVariable String teamName,
            @Valid @ModelAttribute("teamModel") AddTeamDto teamModel,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            prepareEditForm(model, teamName);
            return "team-add";
        }
        try {
            teamService.updateTeam(teamName, teamModel);
        } catch (TeamAlreadyExistsException exception) {
            bindingResult.rejectValue("name", "duplicate", exception.getMessage());
            prepareEditForm(model, teamName);
            return "team-add";
        }
        redirectAttributes.addFlashAttribute(
                "successMessage", "Команда успешно изменена"
        );
        redirectAttributes.addAttribute("teamName", teamModel.getName());
        return "redirect:/teams/{teamName}";
    }

    @PostMapping("/{teamName}/delete")
    public String deleteTeam(
            @PathVariable String teamName,
            RedirectAttributes redirectAttributes
    ) {
        teamService.removeTeam(teamName);
        redirectAttributes.addFlashAttribute("successMessage", "Команда '" + teamName + "' удалена");
        return "redirect:/teams";
    }

    private void prepareCreateForm(Model model) {
        model.addAttribute("editing", false);
        model.addAttribute("formHeading", "Новая команда");
        model.addAttribute("submitLabel", "Создать команду");
    }

    private void prepareEditForm(Model model, String originalName) {
        model.addAttribute("editing", true);
        model.addAttribute("originalName", originalName);
        model.addAttribute("formHeading", "Изменение команды");
        model.addAttribute("submitLabel", "Сохранить изменения");
    }
}
