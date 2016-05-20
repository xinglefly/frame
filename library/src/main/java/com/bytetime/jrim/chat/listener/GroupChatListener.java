package com.bytetime.jrim.chat.listener;

import android.content.Context;

import com.easemob.chat.GroupChangeListener;

public class GroupChatListener implements GroupChangeListener {
    private Context context;

    public GroupChatListener(Context context) {
        this.context = context;
    }

    @Override
    public void onInvitationReceived(String groupId, String groupName, String inviter, String reason) {

    }

    @Override
    public void onApplicationReceived(String groupId, String groupName, String applier, String reason) {

    }

    @Override
    public void onApplicationAccept(String groupId, String groupName, String acceptor) {

    }

    @Override
    public void onApplicationDeclined(String groupId, String groupName, String decliner,
                                      String reason) {

    }

    @Override
    public void onInvitationAccpted(String groupId, String inviter, String reason) {

    }

    @Override
    public void onInvitationDeclined(String groupId, String invitee, String reason) {

    }

    @Override
    public void onUserRemoved(String groupId, String groupName) {

    }

    @Override
    public void onGroupDestroy(String groupId, String groupName) {

    }
}
