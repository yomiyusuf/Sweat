package com.yomi.sweat.network;

public class Resource<T> {
    private Status status;
    private T data;
    private AppException exception;
    private ErrorType errorType;

    public Resource(Status status, T data, AppException exception, ErrorType errorType) {
        this.status = status;
        this.data = data;
        this.exception = exception;
        this.errorType = errorType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public AppException getException() {
        return exception;
    }

    public void setException(AppException exception) {
        this.exception = exception;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

}

class AppException{
    private Throwable exception;

    public AppException(Throwable exception){
        this.exception = exception;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }
}

enum Status {
    SUCCESS, ERROR, LOADING
}

enum ErrorType {
    NETWORK_UNAVAILABLE, SERVER_ERROR
}
