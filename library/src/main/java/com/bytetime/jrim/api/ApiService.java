package com.bytetime.jrim.api;

import com.bytetime.jrim.api.response.LoginResponse;
import com.bytetime.jrim.data.model.User;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

import retrofit2.http.GET;

public interface ApiService {
    @POST("tokens")
    @FormUrlEncoded
    Observable<LoginResponse> login(@Field("username") String username,
                                    @Field("password") String password);

    @GET("users/{username}")
    Observable<User> getUser(@Path("username") String username);
}
