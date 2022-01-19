package com.example.passkeeper.data.retrofit;

public class Resource<T> {
    public enum Status { SUCCESS, ERROR, WAITING, NONE}

    private final T data;
    private final Status dataStatus;
    private final String errorText;
    private boolean isHandled;

    public Resource(T data, Status dataStatus, String errorText) {
        this.data = data;
        this.dataStatus = dataStatus;
        this.errorText = errorText;
        this.isHandled = false;
    }

    public T getDataNotHandled() {
        if (!isHandled) {
            return getData();
        }
        return null;
    }

    public T getData() {
        setHandled();
        return data;
    }

    public Status getStatus() {
        return dataStatus;
    }

    public String getError() {
        return errorText;
    }

    public boolean isHandled() {
        return isHandled;
    }

    public void setHandled() {
        isHandled = true;
    }

    public boolean isComplete() {
        return (dataStatus == Status.SUCCESS) || (dataStatus == Status.ERROR);
    }

    public static<T> Resource<T> WAITING() {
        return new Resource<>(null, Status.WAITING, null);
    }

    public static<T> Resource<T> WAITING(T data) {
        return new Resource<>(data, Status.WAITING, null);
    }

    public static<T> Resource<T> NONE() {
        return new Resource<>(null, Status.NONE, null);
    }

    public static<T> Resource<T> ERROR(String errorText) {
        return new Resource<>(null, Status.ERROR, errorText);
    }

    public static<T> Resource<T> SUCCESS(T data) {
        return new Resource<>(data, Status.SUCCESS, null);
    }
}
