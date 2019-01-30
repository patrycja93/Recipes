package com.example.recipeslistapplication.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.recipeslistapplication.R;
import com.example.recipeslistapplication.databinding.ActivityMainBinding;
import com.example.recipeslistapplication.view.adapter.RecipeAdapter;
import com.example.recipeslistapplication.viewmodel.RecipeViewModel;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private ActivityMainBinding activityMainBinding;
    private RecipeViewModel recipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setUpListOfUsersView(activityMainBinding.listRecipes);
        setUpObserver(recipeViewModel);

        recipeViewModel.getRecipes();
    }

    private void initDataBinding() {
        activityMainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        recipeViewModel = new RecipeViewModel(this);
        activityMainBinding.setRecipeViewModel(recipeViewModel);
    }

    private void setUpListOfUsersView(RecyclerView recipesList) {
        RecipeAdapter userAdapter = new RecipeAdapter();
        recipesList.setAdapter(userAdapter);
        recipesList.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setUpObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recipeViewModel.reset();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof  RecipeViewModel) {
            RecipeAdapter userAdapter = (RecipeAdapter) activityMainBinding.listRecipes.getAdapter();
            RecipeViewModel recipeViewModel = (RecipeViewModel) o;
            userAdapter.setRecipeList(recipeViewModel.getRecipeList());
        }
    }
}
