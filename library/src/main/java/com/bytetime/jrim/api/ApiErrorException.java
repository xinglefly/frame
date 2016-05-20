package com.bytetime.jrim.api;

import java.io.IOException;

public class ApiErrorException extends IOException {
    public ApiErrorException(String message) {
        super(message);
    }
}
