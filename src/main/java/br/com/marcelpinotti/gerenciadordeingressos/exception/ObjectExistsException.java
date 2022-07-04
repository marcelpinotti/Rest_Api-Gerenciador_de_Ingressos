package br.com.marcelpinotti.gerenciadordeingressos.exception;

public class ObjectExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ObjectExistsException(String message) {
        super(message);
    }
}
