package com.tester.tester.Service;

public enum ResultCodeService {

    SUCCESS(200),
    FAIL(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    SERVER_ERROR(500);

    public int code;

    ResultCodeService(int code) {
        this.code = code;
    }
}
