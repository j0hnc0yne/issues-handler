package com.demo.issues_handler.exception;

public class RepoNotFoundException extends IssuesException {
    public RepoNotFoundException(String message) {
        super(message);
    }

    public RepoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepoNotFoundException(Throwable cause) {
        super(cause);
    }
}