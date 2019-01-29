package com.example.recipeslistapplication.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.recipeslistapplication.R;
import com.example.recipeslistapplication.databinding.ActivityMainBinding;
import com.example.recipeslistapplication.viewmodel.RecipeViewModel;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private ActivityMainBinding activityMainBinding;
    private RecipeViewModel recipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setUpObserver(recipeViewModel);
    }

    private void initDataBinding() {
        activityMainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        recipeViewModel = new RecipeViewModel(this);
        activityMainBinding.setRecipeViewModel(recipeViewModel);
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

    }
}
