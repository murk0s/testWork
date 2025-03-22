package ru.systems1221.testwork.exception;

public class UserCreateException extends RuntimeException{
    public UserCreateException(String message) {
        super(message);
    }
}
