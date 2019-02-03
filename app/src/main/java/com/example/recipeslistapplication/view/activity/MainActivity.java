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
import android.support.v7.widget.SearchView;


import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private ActivityMainBinding activityMainBinding;
    private RecipeViewModel recipeViewModel;
    private RecipeAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setUpListView(activityMainBinding.listRecipes);
        setUpObserver(recipeViewModel);
        recipeViewModel.getRecipes();

        activityMainBinding.search.setActivated(true);
        activityMainBinding.search.setQueryHint("Enter text to search");
        activityMainBinding.search.onActionViewExpanded();
        activityMainBinding.search.setIconified(false);
        activityMainBinding.search.clearFocus();

        activityMainBinding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recipeAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void initDataBinding() {
        activityMainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        recipeViewModel = new RecipeViewModel(this);
        activityMainBinding.setRecipeViewModel(recipeViewModel);
    }

    private void setUpListView(RecyclerView recipesList) {
        recipeAdapter = new RecipeAdapter();
        recipesList.setAdapter(recipeAdapter);
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
            RecipeAdapter recipeAdapter = (RecipeAdapter) activityMainBinding.listRecipes.getAdapter();
            RecipeViewModel recipeViewModel = (RecipeViewModel) o;
            recipeAdapter.setRecipeList(recipeViewModel.getRecipeList());
        }
    }
}
