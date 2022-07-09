package br.com.marcelpinotti.gerenciadordeingressos.exception.handler;

import br.com.marcelpinotti.gerenciadordeingressos.exception.ErrorObject;

import java.io.Serializable;
import java.util.List;

public class StandardErrorValidation implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long timestamp;
    private Integer status;
    private String message;
    private List<ErrorObject> errors;

    public StandardErrorValidation() {
    }

    public StandardErrorValidation(Long timestamp, Integer status, String message, List<ErrorObject> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorObject> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorObject> errors) {
        this.errors = errors;
    }
}