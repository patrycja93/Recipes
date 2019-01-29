package com.example.recipeslistapplication.model;


import java.util.List;

public class Recipe {

    private String title;
    private String description;
    private List<Ingredient> ingredients;
    private List<Image> images;

    public Recipe() {
    }

    public Recipe(String title, String description, List<Ingredient> ingredients, List<Image> images) {
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
