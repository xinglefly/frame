package com.bytetime.jrim.events;

import com.easemob.chat.EMMessage;

public class NewMessageEvent {
    public EMMessage message;

    public NewMessageEvent(EMMessage message) {
        this.message = message;
    }
}
