package com.cloud.cqc.exception;

public class TumourException extends RuntimeException {

    public TumourException(String message) {
        super(message);
    }

    public static void fail(String msg) {

        throw new TumourException(msg);
    }

}