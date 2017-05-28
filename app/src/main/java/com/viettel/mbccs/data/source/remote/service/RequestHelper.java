package com.viettel.mbccs.data.source.remote.service;

import com.viettel.mbccs.BuildConfig;
import com.viettel.mbccs.config.Config;
import com.viettel.mbccs.data.source.local.UserLocalDataSource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestHelper {

    public static MBCSSApi instance1;
    public static MBCSSApi instance2;

    private static final int CONNECTION_TIMEOUT = 30;

    public static MBCSSApi getRequest() {
        if (instance1 == null) {
            instance1 = getRequest(false, false);
        }
        return instance1;
    }

    public static MBCSSApi getRequestHeader() {
        return getRequest(true, false);
    }

    public static MBCSSApi getRequestMultipart() {
        if (instance2 == null) {
            instance2 = getRequest(false, true);
        }
        return instance2;
    }

    public static MBCSSApi getRequest(boolean isHeader, boolean isMultiPart) {
        HttpLoggingInterceptor logging = getHttpLoggingInterceptor();
        OkHttpClient client;
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        httpClient.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        httpClient.addInterceptor(logging);
        if (isHeader) {
            httpClient.addInterceptor(requestWithHeader());
        }
        if (isMultiPart) {
            httpClient.addInterceptor(requestMultipart());
        }

        Retrofit retrofit =
                new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(ErrorHandlerFactory.create())
                        .baseUrl(Config.END_POINT)
                        .client(httpClient.build())
                        .build();
        return retrofit.create(MBCSSApi.class);
    }

    private static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        return interceptor;
    }

    private static Interceptor requestWithHeader() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //Token of user when registration is success.
                final String token = UserLocalDataSource.getInstance().getAccessToken();
                Request original = chain.request();
                Request.Builder builder = original.newBuilder()
                        .addHeader("Cache-Control", "no-cache")
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + token)
                        .method(original.method(), original.body());
                Response response = chain.proceed(builder.build());
                return response;
            }
        };
    }

    private static Interceptor requestMultipart() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder builder = original.newBuilder()
                        .addHeader("Content-Type", "multipart/form-data")
                        .header("Authorization", "no")
                        .method(original.method(), original.body());
                Response response = chain.proceed(builder.build());
                return response;
            }
        };
    }
}