package com.bytetime.jrim;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class JRIMModule {
    private Application application;

    public JRIMModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {return this.application; }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return this.application.getApplicationContext();
    }
}
