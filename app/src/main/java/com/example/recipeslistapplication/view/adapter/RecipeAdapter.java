package com.example.recipeslistapplication.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.recipeslistapplication.R;
import com.example.recipeslistapplication.databinding.RecipeItemBinding;
import com.example.recipeslistapplication.model.Recipe;
import com.example.recipeslistapplication.viewmodel.RecipeItem;

import java.util.Collections;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder> {

    private List<Recipe> recipeList;

    public RecipeAdapter() {this.recipeList = Collections.emptyList();}

    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecipeItemBinding itemRecipeBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.recipe_item ,parent, false);
        return new RecipeAdapterViewHolder(itemRecipeBinding);
    }

    @Override
    public void onBindViewHolder(RecipeAdapterViewHolder holder, int position) {
        holder.bindRecipe(recipeList.get(position));
    }

    @Override
    public int getItemCount() {
        return  recipeList.size();
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
        notifyDataSetChanged();
    }

    public static class RecipeAdapterViewHolder extends RecyclerView.ViewHolder {

        RecipeItemBinding itemRecipeBinding;

        public RecipeAdapterViewHolder(RecipeItemBinding recipeBinding) {
            super(recipeBinding.item);
            this.itemRecipeBinding = recipeBinding;
        }

        void bindRecipe(Recipe recipe){
            if(itemRecipeBinding.getRecipeViewModel() == null){
                itemRecipeBinding.setRecipeViewModel(new RecipeItem(recipe, itemView.getContext()));
            }else {
                itemRecipeBinding.getRecipeViewModel().setRecipe(recipe);
            }
        }
    }
}
