package com.example.recipeslistapplication.model;

public class Element {
    private String id;
    private String amount;
    private String hint;
    private String name;
    private String symbol;

    public Element() {
    }

    public Element(String id, String amount, String hint, String name, String symbol) {
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
        this.amount = amount == null ? "" : amount;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint == null ? "" : hint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? "" : symbol;
    }

    public String geElementsToRecipe() {
        String str = "";
        str += name == null ? "" : name + " ";
        str += amount == null ? "" : amount + " ";
        str += symbol == null ? "" : symbol + " ";
        return str + "\n";
    }

}
