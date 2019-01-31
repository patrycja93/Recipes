package com.example.recipeslistapplication.model;

import java.util.List;

public class Ingredient {
    private String id;
    private String name;
    private List<Element> elements;

    public Ingredient() {
    }

    public Ingredient(String id, String name, List<Element> elements) {
        this.id = id;
        this.name = name;
        this.elements = elements;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }


}
