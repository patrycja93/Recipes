package com.example.interviewapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    private String id;
    private String amount;
    private String hint;
    private String name;
    private String symbol;

}
