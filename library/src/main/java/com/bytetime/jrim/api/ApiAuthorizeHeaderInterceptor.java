package com.bytetime.jrim.api;

import android.util.Base64;

import com.bytetime.jrim.JRIM;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiAuthorizeHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if(JRIM.isLoggedIn()) {
            String credentials = "Avatar:" + JRIM.getCurrentToken();
            final String header = "Basic " + Base64.encodeToString(credentials.getBytes(),
                    Base64.NO_WRAP);
            Request requestWithTokenHeader = request.newBuilder()
                    .header("Authorization", header)
                    .build();
            return chain.proceed(requestWithTokenHeader);
        }

        return chain.proceed(request);
    }
}
