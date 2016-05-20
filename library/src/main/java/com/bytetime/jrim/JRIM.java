package com.bytetime.jrim;

import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.bytetime.jrim.api.response.LoginResponse;
import com.bytetime.jrim.data.database.DaoSession;
import com.bytetime.jrim.data.model.User;
import com.bytetime.jrim.utils.LogUtil;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public class JRIM {

    private static Application application;
    private static JRIMComponent jrimComponent;
    private static boolean debugMode = true;
    private static boolean isLoggedIn = false;
    private static String currentToken = null;
    private static User currentUser = null;

    public static void init(Application app) throws Exception {
        LogUtil.init(debugMode);
        application = app;
        initJRIMComponent(app);
        jrimComponent.getChatService();
    }

    public static boolean isDebugMode() { return debugMode; }

    public static JRIMComponent getJRIMComponent() {
        return jrimComponent;
    }

    public static Application getApplication() {
        return application;
    }

    public static Observable<String> login(String username, String password) {
        return jrimComponent.getApiService().login(username, password)
                .flatMap(new Func1<LoginResponse, Observable<String>>() {
                    @Override
                    public Observable<String> call(LoginResponse loginResponse) {
                        LogUtil.d("RestApi login success, token: %s, ease_user: %s, ease_pass: %s",
                                loginResponse.token.token, loginResponse.user.ease_account,
                                loginResponse.user.ease_password);

                        String easemobUsername = loginResponse.user.ease_account;
                        String easemobPassword = loginResponse.user.ease_password;

                        currentToken = loginResponse.token.token;
                        currentUser = loginResponse.user;

                        return jrimComponent.getChatService()
                                .login(easemobUsername, easemobPassword);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        isLoggedIn = true;
                        return makeSureConversationAndGroupLoaded();
                    }
                });
    }

    public static Observable<User> getUserInfo(String username) {
        return getJRIMComponent().getApiService().getUser(username);
    }

    public static Observable<String> makeSureConversationAndGroupLoaded() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                EMGroupManager.getInstance().loadAllGroups();
                EMChatManager.getInstance().loadAllConversations();
                LogUtil.d("Loaded group and conversations!");
                subscriber.onNext("Ok");
                subscriber.onCompleted();
            }
        });
    }

    public static DaoSession getDaoSession() {
        return jrimComponent.getDaoSession();
    }

    public static void logout() {
        isLoggedIn = false;
        currentToken = null;
        currentUser = null;
        EMChatManager.getInstance().logout();
    }

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }

    public static String getCurrentToken()
    {
        return currentToken;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void registerEventBus(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregisterEventBus(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void postEvent(Object event) {
        EventBus.getDefault().post(event);
    }

    private static void initJRIMComponent(Application application) throws Exception {
        String packageName = application.getPackageName();
        Bundle metaData = application.getApplicationContext()
                .getPackageManager()
                .getApplicationInfo(packageName, PackageManager.GET_META_DATA)
                .metaData;

        ArrayList<String> mustHaveKeyList = new ArrayList<>();
        mustHaveKeyList.add("JRIM_API_HOST");
        mustHaveKeyList.add("EASEMOB_API_URL");
        mustHaveKeyList.add("EASEMOB_CHAT_ADDRESS");
        mustHaveKeyList.add("EASEMOB_CHAT_PORT");
        mustHaveKeyList.add("EASEMOB_APPKEY");

        for (String key: mustHaveKeyList) {
            if(!metaData.containsKey(key))
            {
                throw new Exception("JRIM initial failed, missing metadata key: " + key);
            }
            if(metaData.get(key) == null)
            {
                throw new Exception("JRIM initial failed, empty metadata key: " + key);
            }
        }

        jrimComponent = DaggerJRIMComponent.builder()
                .jRIMModule(new JRIMModule(application))
                .build();
    }
}
