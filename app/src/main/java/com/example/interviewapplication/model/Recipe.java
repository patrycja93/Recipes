package com.example.interviewapplication.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    private String title;
    private String description;
    private List<Ingredient> ingredients;
    private List<Image> images;

}
