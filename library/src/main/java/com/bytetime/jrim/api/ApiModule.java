package com.bytetime.jrim.api;

import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.bytetime.jrim.JRIM;
import com.bytetime.jrim.utils.LogUtil;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

@Module
public class ApiModule {
    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Application application) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if(JRIM.isDebugMode())
        {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);

            setProxy(application, builder);
        }

        ApiErrorInterceptor errorHandler = new ApiErrorInterceptor();
        builder.addInterceptor(errorHandler);

        ApiAuthorizeHeaderInterceptor authorizeInterceptor = new ApiAuthorizeHeaderInterceptor();
        builder.addInterceptor(authorizeInterceptor);

        builder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);

        return builder.build();
    }

    private void setProxy(Application application, OkHttpClient.Builder builder) {
        try{
            String packageName = application.getPackageName();
            Bundle metaData = application.getApplicationContext().getPackageManager()
                    .getApplicationInfo(packageName, PackageManager.GET_META_DATA)
                    .metaData;
            if(metaData.containsKey("JRIM_ENABLE_PROXY") &&
                    metaData.containsKey("JRIM_PROXY_HOST") &&
                    metaData.containsKey("JRIM_PROXY_PORT")) {
                boolean enableProxy = metaData.getInt("JRIM_ENABLE_PROXY") == 1;
                String proxyHost = metaData.getString("JRIM_PROXY_HOST");
                int proxyPort = metaData.getInt("JRIM_PROXY_PORT");

                if(enableProxy && proxyHost != null && proxyPort > 0)
                {
                    LogUtil.i("Proxy enabled, proxyHost: %s, proxyPort: %d", proxyHost, proxyPort);
                    builder.proxy(new Proxy(Proxy.Type.HTTP,
                            InetSocketAddress.createUnresolved(proxyHost, proxyPort)));
                }
            }
        }
        catch (Exception e)
        {
            LogUtil.e("get proxy setting exception: %s", e.getMessage());
        }
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Application application, OkHttpClient client) {
        Retrofit.Builder builder = new Retrofit.Builder();
        try{
            String packageName = application.getPackageName();
            Bundle metaData = application.getApplicationContext()
                    .getPackageManager()
                    .getApplicationInfo(packageName, PackageManager.GET_META_DATA)
                    .metaData;
            String apiHost = metaData.getString("JRIM_API_HOST");

            assert apiHost != null;

            if(!apiHost.endsWith("/"))
                apiHost += "/";

            builder.client(client)
                    .baseUrl(apiHost)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());

            return builder.build();
        }
        catch (Exception e)
        {
            LogUtil.e("provide Retrofit failed, Exception: %s", e.getMessage());
        }

        return null;
    }

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
