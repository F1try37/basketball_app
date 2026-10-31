package ru.rutmiit.basketball.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.rutmiit.basketball.services.PlayerService;

@Controller
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    public String players(Model model) {
        model.addAttribute("playerInfos", playerService.allPlayers());
        return "player-all";
    }
}
