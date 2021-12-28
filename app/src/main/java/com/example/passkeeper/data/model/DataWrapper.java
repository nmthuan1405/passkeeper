package com.example.passkeeper.data.model;

public class DataWrapper<T> {
    public enum Status { SUCCESS, ERROR, WAITING }

    T data;
    Status dataStatus;

    public DataWrapper(T data, Status dataStatus) {
        this.data = data;
        this.dataStatus = dataStatus;
    }

    public Status getStatus() {
        return dataStatus;
    }

    public static<T> DataWrapper<T> WAITING() {
        return new DataWrapper<>(null, Status.WAITING);
    }

    public static<T> DataWrapper<T> ERROR() {
        return new DataWrapper<>(null, Status.ERROR);
    }

    public static<T> DataWrapper<T> SUCCESS(T data) {
        return new DataWrapper<>(data, Status.SUCCESS);
    }
}
