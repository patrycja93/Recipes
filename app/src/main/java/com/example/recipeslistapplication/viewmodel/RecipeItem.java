package com.example.recipeslistapplication.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.recipeslistapplication.model.Element;
import com.example.recipeslistapplication.model.Ingredient;
import com.example.recipeslistapplication.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeItem extends BaseObservable {

    private Recipe recipe;
    private Context context;

    public RecipeItem(Recipe recipe, Context context) {
        this.recipe = recipe;
        this.context = context;
    }

    public String getTitle() {
        return recipe.getTitle();
    }

    public String getDescription() {
        return recipe.getDescription();
    }

    public String getIngredientsToRecipe() {
        String str = "";
            for (Element e :getElement())
                str += e.geElementsToRecipe();
        return str;
    }

    public List<Ingredient> getIngredients() {
        return recipe.getIngredients();
    }

    public List<Element> getElement() {
        List<Element> elementsList = new ArrayList<>();
        for (Ingredient i : getIngredients())
            elementsList.addAll(i.getElements());

        return elementsList;
    }


    @BindingAdapter("imageRecipe")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public String getImage() {
        return recipe.getImages().get(0).getUrl();
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        notifyChange();
    }

}
