package ru.rutmiit.basketball.services.exceptions;

public class TeamNotFoundException extends RuntimeException {

    public TeamNotFoundException(String teamName) {
        super("Команда с названием '" + teamName + "' не найдена");
    }
}
