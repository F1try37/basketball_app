package ru.rutmiit.basketball.services.exceptions;

public class TeamAlreadyExistsException extends RuntimeException {

    public TeamAlreadyExistsException() {
        super("Команда с таким названием уже существует");
    }
}
