package br.com.marcelpinotti.gerenciadordeingressos.exception;

import java.io.Serializable;

public class ErrorObject implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;
    private String propertyPath;

    public ErrorObject() {
    }

    public ErrorObject(String message, String propertyPath) {
        this.message = message;
        this.propertyPath = propertyPath;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPropertyPath() {
        return propertyPath;
    }

    public void setPropertyPath(String propertyPath) {
        this.propertyPath = propertyPath;
    }
}