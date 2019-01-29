package com.example.recipeslistapplication.model;

public class Ingredient {
    private String id;
    private String amount;
    private String hint;
    private String name;
    private String symbol;

    public Ingredient() {
    }

    public Ingredient(String id, String amount, String hint, String name, String symbol) {
        this.id = id;
        this.amount = amount;
        this.hint = hint;
        this.name = name;
        this.symbol = symbol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
