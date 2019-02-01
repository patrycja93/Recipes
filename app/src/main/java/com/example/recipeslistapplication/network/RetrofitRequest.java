package com.example.recipeslistapplication.network;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitRequest {

    public ApiRequest create(Context context) {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File httpCacheDirectory = new File(context.getCacheDir(), "offlineCache");

        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(provideCacheInterceptor())
                .addInterceptor(provideOfflineCacheInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();
        return retrofit.create(ApiRequest.class);
    }


    private Interceptor provideCacheInterceptor() {

        return chain -> {
            Request request = chain.request();
            Response originalResponse = chain.proceed(request);
            String cacheControl = originalResponse.header("Cache-Control");

            if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                    cacheControl.contains("must-revalidate") || cacheControl.contains("max-stale=0")) {

                CacheControl cc = new CacheControl.Builder()
                        .maxStale(1, TimeUnit.DAYS)
                        .build();

                request = request.newBuilder()
                        .cacheControl(cc)
                        .build();

                return chain.proceed(request);

            } else {
                return originalResponse;
            }
        };

    }


    private Interceptor provideOfflineCacheInterceptor() {

        return chain -> {
            try {
                return chain.proceed(chain.request());
            } catch (Exception e) {

                CacheControl cacheControl = new CacheControl.Builder()
                        .onlyIfCached()
                        .maxStale(1, TimeUnit.DAYS)
                        .build();

                Request offlineRequest = chain.request().newBuilder()
                        .cacheControl(cacheControl)
                        .build();
                return chain.proceed(offlineRequest);
            }
        };
    }
}
