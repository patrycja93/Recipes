package com.example.recipeslistapplication.application;

import android.app.Application;
import android.content.Context;

import com.example.recipeslistapplication.network.ApiRequest;
import com.example.recipeslistapplication.network.RetrofitRequest;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class AppController extends Application {

    private ApiRequest apiRequest;
    private Scheduler scheduler;

    private static AppController get(Context context) {
        return (AppController) context.getApplicationContext();
    }

    public static AppController create(Context context) {
        return AppController.get(context);
    }

    public ApiRequest getService() {
        if (apiRequest == null) {
            apiRequest = RetrofitRequest.create();
        }
        return apiRequest;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }
        return scheduler;
    }
}
