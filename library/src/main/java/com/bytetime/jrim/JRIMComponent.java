package com.bytetime.jrim;


import android.view.ViewGroup;

import com.bytetime.jrim.api.ApiModule;
import com.bytetime.jrim.api.ApiService;
import com.bytetime.jrim.chat.ChatModule;
import com.bytetime.jrim.chat.ChatService;
import com.bytetime.jrim.data.DataModule;
import com.bytetime.jrim.data.database.DaoSession;
import com.bytetime.jrim.ui.component.LoginComponent;
import com.bytetime.jrim.ui.module.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {JRIMModule.class, ApiModule.class, ChatModule.class, DataModule.class})
public interface JRIMComponent {
    void inject(ViewGroup viewGroup);

    LoginComponent plus(LoginModule module);

    ApiService getApiService();
    ChatService getChatService();
    DaoSession getDaoSession();
}
