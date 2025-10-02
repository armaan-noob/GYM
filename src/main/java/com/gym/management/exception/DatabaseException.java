package com.gym.management.exception;

public class DatabaseException extends Exception {
    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public static DatabaseException connectionError(Throwable cause) {
        return new DatabaseException("Database connection error", cause);
    }

    public static DatabaseException queryError(String query, Throwable cause) {
        return new DatabaseException("Database query error: " + query, cause);
    }
}