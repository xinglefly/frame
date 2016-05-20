package com.bytetime.jrim.events;

import com.bytetime.jrim.data.JRIMError;

public class ErrorEvent {
    private JRIMError error;
    private String message;

    public ErrorEvent(JRIMError error, String message) {
        this.error = error;
        this.message = message;
    }
}
