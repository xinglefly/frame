package com.bytetime.jrim.chat.listener;

import android.content.Context;

import com.easemob.chat.EMContactListener;

import java.util.List;

public class ContactListener implements EMContactListener {
    private Context context;

    public ContactListener(Context context) {
        this.context = context;
    }


    @Override
    public void onContactAdded(List<String> usernameList) {

    }

    @Override
    public void onContactDeleted(List<String> usernameList) {

    }

    @Override
    public void onContactInvited(String username, String reason) {

    }

    @Override
    public void onContactAgreed(String username) {

    }

    @Override
    public void onContactRefused(String username) {

    }
}
