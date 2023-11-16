package com.moreno.obituaries.core;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class AuthInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request.Builder newRequest = chain.request().newBuilder();
        if (Hibernate.getToken() != null) {
            newRequest.addHeader("Authorization", "Bearer " + Hibernate.getToken());
        }
        return chain.proceed(newRequest.build());
    }
}
