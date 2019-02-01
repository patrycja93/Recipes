package com.example.recipeslistapplication.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.recipeslistapplication.R;
import com.example.recipeslistapplication.databinding.RecipeItemBinding;
import com.example.recipeslistapplication.model.Ingredient;
import com.example.recipeslistapplication.model.Recipe;
import com.example.recipeslistapplication.viewmodel.RecipeItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder> implements Filterable {

    private List<Recipe> recipeList;
    List<Recipe> mFilterList;
    ValueFilter valueFilter;
    private LayoutInflater inflater;

    public RecipeAdapter() {
        this.recipeList = Collections.emptyList();
    }

    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecipeItemBinding itemRecipeBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.recipe_item, parent, false);
        return new RecipeAdapterViewHolder(itemRecipeBinding);
    }

    @Override
    public void onBindViewHolder(RecipeAdapterViewHolder holder, int position) {
        holder.bindRecipe(recipeList.get(position));
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
        this.mFilterList = recipeList;
        notifyDataSetChanged();
    }

    public static class RecipeAdapterViewHolder extends RecyclerView.ViewHolder {

        RecipeItemBinding itemRecipeBinding;

        public RecipeAdapterViewHolder(RecipeItemBinding recipeBinding) {
            super(recipeBinding.item);
            this.itemRecipeBinding = recipeBinding;
        }

        void bindRecipe(Recipe recipe) {
            if (itemRecipeBinding.getRecipeViewModel() == null) {
                itemRecipeBinding.setRecipeViewModel(new RecipeItem(recipe, itemView.getContext()));
            } else {
                itemRecipeBinding.getRecipeViewModel().setRecipe(recipe);
            }
        }
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List<Recipe> filterList = new ArrayList<>();
                for (Recipe recipe : mFilterList) {
                    for (Ingredient ingredient : recipe.getIngredients())
                        if ((recipe.getTitle().toUpperCase()).contains(constraint.toString().toUpperCase())
                                || ingredient.getNameOfIngredientAndElements().toUpperCase().contains(constraint.toString().toUpperCase())) {
                            filterList.add(recipe);
                        }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mFilterList.size();
                results.values = mFilterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            recipeList = (List<Recipe>) results.values;
            notifyDataSetChanged();
        }

    }

}
