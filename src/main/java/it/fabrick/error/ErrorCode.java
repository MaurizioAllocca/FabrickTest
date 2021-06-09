package it.fabrick.error;

public enum ErrorCode implements IError {

    ERR_API_000("Generic error", "Generic error");

    private String errorCode;
    private String errorMessage;

    ErrorCode(String errorCode, String errorMessage) {

        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
