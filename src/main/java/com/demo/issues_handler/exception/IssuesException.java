package com.demo.issues_handler.exception;

public class IssuesException extends Exception {
    public IssuesException(String message) {
        super(message);
    }

    public IssuesException(String message, Throwable cause) {
        super(message, cause);
    }

    public IssuesException(Throwable cause) {
        super(cause);
    }
}
