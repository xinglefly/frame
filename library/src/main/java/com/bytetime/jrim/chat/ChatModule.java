package com.bytetime.jrim.chat;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ChatModule {
    @Provides
    @Singleton
    ChatService provideChatService(Application application) {
        return new ChatService(application.getApplicationContext());
    }
}
