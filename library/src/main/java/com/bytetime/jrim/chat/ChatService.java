package com.bytetime.jrim.chat;

import android.content.Context;

import com.bytetime.jrim.JRIM;
import com.bytetime.jrim.chat.listener.ChatConnectionListener;
import com.bytetime.jrim.chat.listener.ChatEventListener;
import com.bytetime.jrim.chat.listener.ContactListener;
import com.bytetime.jrim.chat.listener.GroupChatListener;
import com.bytetime.jrim.utils.LogUtil;
import com.bytetime.jrim.utils.SystemUtil;
import com.easemob.EMCallBack;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMChatOptions;
import com.easemob.chat.EMContactManager;
import com.easemob.chat.EMGroupManager;

import rx.Observable;
import rx.Subscriber;

public class ChatService {
    private ChatConnectionListener connectionListener;
    private ContactListener contactListener;
    private GroupChatListener groupChatListener;
    private ChatEventListener chatEventListener;

    public ChatService(Context context){
        initEasemobService(context);
    }

    public void initEasemobService(Context context) {
        int pid = android.os.Process.myPid();
        String processAppName = SystemUtil.getAppName(context, pid);
        String packageName = context.getPackageName();
        if (processAppName == null ||!processAppName.equalsIgnoreCase(packageName)) {
            LogUtil.i("ChatService already initialised");
            return;
        }

        EMChat.getInstance().init(context);
        EMChat.getInstance().setDebugMode(JRIM.isDebugMode());

        EMChatOptions chatOptions = EMChatManager.getInstance().getChatOptions();
        chatOptions.setRequireAck(true);
        chatOptions.setRequireDeliveryAck(true);
        chatOptions.setNotifyBySoundAndVibrate(false);
        chatOptions.setNoticeBySound(false);
        chatOptions.setNoticedByVibrate(false);
        chatOptions.setUseSpeaker(false);
        chatOptions.setAcceptInvitationAlways(false);
        chatOptions.setShowNotificationInBackgroud(false);

        registerConnectionListener(context);
        registerContactListener(context);
        registerGroupChatListener(context);
        registerChatEventListener(context);

        EMChat.getInstance().setAppInited();
    }

    private void registerConnectionListener(Context context) {
        connectionListener = new ChatConnectionListener(context);
        EMChatManager.getInstance().addConnectionListener(connectionListener);
    }

    private void registerContactListener(Context context) {
        contactListener = new ContactListener(context);
        EMContactManager.getInstance().setContactListener(contactListener);
    }

    private void registerGroupChatListener(Context context) {
        groupChatListener = new GroupChatListener(context);
        EMGroupManager.getInstance().addGroupChangeListener(groupChatListener);
    }

    private void registerChatEventListener(Context context) {
        chatEventListener = new ChatEventListener(context);
        EMChatManager.getInstance().registerEventListener(chatEventListener);
    }

    public Observable<String> login(final String username, final String password) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                EMChatManager.getInstance().login(username, password, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        LogUtil.d("Easemob login success!");
                        subscriber.onNext("Ok");
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(int i, String s) {
                        subscriber.onError(new Exception("Easemob login failed: " + s));
                    }

                    @Override
                    public void onProgress(int i, String s) {
                    }
                });
            }
        });
    }

}
