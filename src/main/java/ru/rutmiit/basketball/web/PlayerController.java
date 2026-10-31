package ru.rutmiit.basketball.web;

import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rutmiit.basketball.dto.AddPlayerDto;
import ru.rutmiit.basketball.models.enums.Position;
import ru.rutmiit.basketball.services.PlayerService;
import ru.rutmiit.basketball.services.TeamService;
import ru.rutmiit.basketball.services.exceptions.TeamNotFoundException;

@Controller
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;
    private final TeamService teamService;

    public PlayerController(PlayerService playerService, TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @ModelAttribute("playerModel")
    public AddPlayerDto playerForm() {
        return new AddPlayerDto();
    }

    @GetMapping
    public String players(Model model) {
        model.addAttribute("playerInfos", playerService.allPlayers());
        return "player-all";
    }

    @GetMapping("/add")
    public String addPlayerForm(Model model) {
        prepareForm(model);
        return "player-add";
    }

    @PostMapping("/add")
    public String addPlayer(@Valid @ModelAttribute("playerModel") AddPlayerDto form,
                            BindingResult bindingResult, Model model,
                            RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasErrors()) {
            try {
                playerService.addPlayer(form);
                redirectAttributes.addFlashAttribute("successMessage", "Игрок добавлен");
                return "redirect:/players";
            } catch (TeamNotFoundException exception) {
                bindingResult.rejectValue("teamName", "missing", "Выбранная команда не найдена");
            } catch (DataIntegrityViolationException exception) {
                bindingResult.reject("integrity", "Игрок не сохранён. Проверьте команду и значения полей");
            }
        }
        prepareForm(model);
        return "player-add";
    }

    private void prepareForm(Model model) {
        model.addAttribute("teams", teamService.allTeams());
        model.addAttribute("positions", Position.values());
    }
}
