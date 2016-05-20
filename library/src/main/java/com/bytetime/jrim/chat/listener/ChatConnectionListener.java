package com.bytetime.jrim.chat.listener;

import android.content.Context;

import com.bytetime.jrim.JRIM;
import com.bytetime.jrim.data.JRIMError;
import com.bytetime.jrim.events.ErrorEvent;
import com.easemob.EMConnectionListener;
import com.easemob.EMError;
import com.easemob.util.NetUtils;

public class ChatConnectionListener implements EMConnectionListener {
    private Context context;

    public ChatConnectionListener(Context context) {
        this.context = context;
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisconnected(int error) {
        switch (error){
            case EMError.USER_REMOVED:
                JRIM.postEvent(new ErrorEvent(JRIMError.CHAT_USER_REMOVED,
                        "聊天账户被删除，请与管理员联系"));
                break;
            case EMError.CONNECTION_CONFLICT:
                JRIM.postEvent(new ErrorEvent(JRIMError.CHAT_CONNECTION_CONFLICT,
                        "账户在其他设备登陆"));
                break;
            default:
                if(!NetUtils.hasNetwork(context)){
                    JRIM.postEvent(new ErrorEvent(JRIMError.NETWORK_NOT_REACHABLE,
                            "无法连接互联网，请检查网络连接"));
                }
        }

    }
}
