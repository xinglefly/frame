package com.bytetime.jrim.api;


import com.bytetime.jrim.api.response.ErrorResponse;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiErrorInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        if(response.code() != 200) {
            String errorBody = response.body().string();
            ErrorResponse errorResponse = new GsonBuilder().create()
                    .fromJson(errorBody, ErrorResponse.class);
            throw new ApiErrorException(errorResponse.message);
        }

        return response;
    }
}
