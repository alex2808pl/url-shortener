package de.telran.urlshortener.security.exception;

public class ResponseException extends Exception{
    public ResponseException(String message) {
        super(message);
    }
}