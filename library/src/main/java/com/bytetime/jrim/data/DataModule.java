package com.bytetime.jrim.data;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.bytetime.jrim.data.database.DaoMaster;
import com.bytetime.jrim.data.database.DaoSession;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {
    @Provides
    @Singleton
    public DaoSession provideDaoSession(Application application) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(
                application.getApplicationContext(), "jrim-db", null);

        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }
}
