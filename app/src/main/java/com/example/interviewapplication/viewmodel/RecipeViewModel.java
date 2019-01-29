package com.example.interviewapplication.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.interviewapplication.application.AppController;
import com.example.interviewapplication.model.Recipe;
import com.example.interviewapplication.network.RecipesResources;

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
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public RecipeViewModel(Context context) {
        this.recipeList = new ArrayList<>();
        this.context = context;
    }

    public void getRecipes(View view) {
        loadRecipeFromServer();
    }

    private void loadRecipeFromServer() {

        AppController appController = AppController.create(context);
        RecipesResources recipesResources = appController.getService();

        Disposable disposable = recipesResources.getRecipes()
                .subscribeOn(appController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::updateRecipesList,
                        throwable -> Log.d(TAG, "loadRecipeFromServer: " + throwable.getMessage()));

        compositeDisposable.add(disposable);
    }

    private void updateRecipesList(List<Recipe> recipes) {
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
}
