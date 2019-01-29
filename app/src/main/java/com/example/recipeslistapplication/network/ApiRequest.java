
package com.example.recipeslistapplication.network;

import com.example.recipeslistapplication.model.Recipe;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiRequest {

    @GET("getRecipesListDetailed?tags=&size=thumbnail-medium&ratio=1&limit=50&from=0")
    Observable<List<Recipe>> getRecipes();
}
