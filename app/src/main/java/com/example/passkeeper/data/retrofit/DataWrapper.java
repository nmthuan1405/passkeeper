package com.example.passkeeper.data.retrofit;

public class DataWrapper<T> {
    public enum Status { SUCCESS, ERROR, WAITING }

    T data;
    Status dataStatus;
    String errorText;

    public DataWrapper(T data, Status dataStatus, String errorText) {
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


    public static<T> DataWrapper<T> WAITING() {
        return new DataWrapper<>(null, Status.WAITING, null);
    }

    public static<T> DataWrapper<T> ERROR(String errorText) {
        return new DataWrapper<>(null, Status.ERROR, errorText);
    }

    public static<T> DataWrapper<T> SUCCESS(T data) {
        return new DataWrapper<>(data, Status.SUCCESS, null);
    }
}
