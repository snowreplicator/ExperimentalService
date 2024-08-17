package ru.experimentalservice.Service.Exception;

public class DuplicateCodeException extends BaseException {
    private final String code;

    public DuplicateCodeException(String code) {
        super("Code: " + code + " already exists.");
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}