package com.bytetime.jrim.chat.listener;

import android.content.Context;

import com.bytetime.jrim.JRIM;
import com.bytetime.jrim.data.database.Message;
import com.bytetime.jrim.data.database.MessageDao;
import com.bytetime.jrim.events.NewMessageEvent;
import com.bytetime.jrim.utils.LogUtil;
import com.easemob.EMEventListener;
import com.easemob.EMNotifierEvent;
import com.easemob.chat.EMMessage;

import java.util.Date;
import java.util.List;

public class ChatEventListener implements EMEventListener {
    private Context context;

    public ChatEventListener(Context context) {
        this.context = context;
    }

    @Override
    public void onEvent(EMNotifierEvent event) {
        switch (event.getEvent()) {
            case EventNewMessage:
                EMMessage message = (EMMessage) event.getData();
                LogUtil.d("Got new message from %s, content: %s",
                        message.getFrom(), message.getBody());

                Message messageData = new Message(null, message.getMsgId(), message.getFrom(),
                        message.getBody().toString(), new Date());
                MessageDao messageDao = JRIM.getDaoSession().getMessageDao();
                messageDao.insert(messageData);

                LogUtil.d("Save message in database.");

                Message messageInDb = messageDao.queryBuilder()
                        .where(MessageDao.Properties.MsgId.eq(message.getMsgId()))
                        .unique();

                LogUtil.d("Got saved message, message from: %s, message content: %s",
                        messageInDb.getFrom(), messageInDb.getContent());

                JRIM.postEvent(new NewMessageEvent(message));
                break;
            case EventDeliveryAck:
                break;
            case EventNewCMDMessage:
                break;
            case EventReadAck:
                break;
            case EventOfflineMessage:
                List<EMMessage> messages = (List<EMMessage>) event.getData();
                break;
            case EventConversationListChanged:
                break;
        }
    }
}
