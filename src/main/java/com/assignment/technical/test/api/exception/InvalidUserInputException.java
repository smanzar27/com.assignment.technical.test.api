package com.assignment.technical.test.api.exception;

public class InvalidUserInputException extends RuntimeException {

    public InvalidUserInputException(String errorMessage) { super(errorMessage); }
}
