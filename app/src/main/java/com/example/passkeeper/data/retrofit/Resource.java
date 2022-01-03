package com.example.passkeeper.data.retrofit;

public class Resource<T> {
    public enum Status { SUCCESS, ERROR, WAITING, NONE}

    T data;
    Status dataStatus;
    String errorText;

    public Resource(T data, Status dataStatus, String errorText) {
        this.data = data;
        this.dataStatus = dataStatus;
        this.errorText = errorText;
    }

    public T getData() {
        return data;
    }

    public Status getStatus() {
        return dataStatus;
    }

    public String getError() {
        return errorText;
    }


    public static<T> Resource<T> WAITING() {
        return new Resource<>(null, Status.WAITING, null);
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
