package com.example.recipeslistapplication.viewmodel;

import android.content.Context;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.example.recipeslistapplication.application.AppController;
import com.example.recipeslistapplication.model.Recipe;
import com.example.recipeslistapplication.network.ApiRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class RecipeViewModel extends Observable {

    private static final String TAG = "RecipeViewModel";

    private Context context;
    private List<Recipe> recipeList;
    public ObservableInt recipeRecycler;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public RecipeViewModel(Context context) {
        this.recipeList = new ArrayList<>();
        this.context = context;
        recipeRecycler = new ObservableInt(View.GONE);
    }

    public void getRecipes() {
        recipeRecycler.set(View.GONE);
        loadRecipeFromServer();
    }

    private void loadRecipeFromServer() {

        AppController appController = AppController.create(context);
        ApiRequest recipesResources = appController.getService();

        Disposable disposable = recipesResources.getRecipes()
                .subscribeOn(appController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::updateRecipesList,
                        throwable -> {
                            Log.d(TAG, throwable.getMessage());
                            recipeRecycler.set(View.GONE);
                        });

        compositeDisposable.add(disposable);
    }

    private void updateRecipesList(List<Recipe> recipes) {
        recipeRecycler.set(View.VISIBLE);
        recipeList.addAll(recipes);
        setChanged();
        notifyObservers();
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }
}
