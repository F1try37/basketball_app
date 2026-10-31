package ru.rutmiit.basketball.web;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.rutmiit.basketball.services.exceptions.TeamNotFoundException;

@ControllerAdvice
public class TeamErrorHandler {

    @ExceptionHandler(TeamNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String teamNotFound(TeamNotFoundException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "error/team-not-found";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String dataConflict(Model model) {
        model.addAttribute("errorMessage", "Изменение нарушает правило целостности данных. Команду с игроками удалить нельзя");
        return "error/data-conflict";
    }
}
