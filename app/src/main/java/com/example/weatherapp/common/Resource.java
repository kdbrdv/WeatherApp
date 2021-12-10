package com.example.weatherapp.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class Resource<T> {

    @NonNull
    public final Status status;
    public final T data;
    public final String msg;

    public Resource(@NonNull Status status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public static <R> Resource<R> success(R data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <R> Resource<R> loading() {
        return new Resource<>(Status.LOADING, null, null);
    }

    public static <R> Resource<R> error(String msg, @Nullable R data) {
        return new Resource<>(Status.ERROR, data, msg);
    }


    public enum Status {
        SUCCESS,
        LOADING,
        ERROR
    }
}

//public class Resource<T> {
//
//
//
////    @NonNull
////    private final String msg;
////    private final T data;
////    private final Status status;
////
////    public Resource(@NonNull String msg, T data, Status status) {
////        this.msg = msg;
////        this.data = data;
////        this.status = status;
////    }
////
////    public static <T>Resource<T> success(T data){
////        return new Resource<>(Status.SUCCESS,data,null);
////    }
////    public static <T>Resource<T> loading(){
////        return new Resource<>(Status.LOADING,null,null);
////    }
////    public static <T>Resource<T> error(@Nullable T data, String msg){
////        return new Resource<>(Status.ERROR,data,msg);
////    }
////
////
////    public enum Status{
////       SUCCESS,
////       ERROR,
////       LOADING
////   }
//
//
//}
