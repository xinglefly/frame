package com.bytetime.jrim.actions;

import com.bytetime.jrim.api.response.ErrorResponse;
import com.bytetime.jrim.utils.LogUtil;
import com.google.gson.GsonBuilder;

import retrofit2.HttpException;
import rx.functions.Action1;

import java.lang.Throwable;

public abstract class ApiErrorAction implements Action1<Throwable> {
    @Override
    public void call(Throwable throwable) {
        ErrorResponse errorResponse = parseErrorResponse(throwable);
        onError(errorResponse);
    }

    public ErrorResponse parseErrorResponse(Throwable e) {
        if (e instanceof HttpException) {
            try {
                retrofit2.Response response = ((HttpException) e).response();
                String errorBody = response.errorBody().string();
                ErrorResponse errorResponse = new GsonBuilder().create()
                        .fromJson(errorBody, ErrorResponse.class);
                return errorResponse;
            } catch (Exception ex) {
                LogUtil.e(ex, "parseErrorResponse exception");
            }
        }
        else {
            LogUtil.e(e, "UnExpect throwable type");
        }

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.message = "JRIM API request failed";
        return errorResponse;
    }

    public abstract void onError(ErrorResponse errorResponse);
}
