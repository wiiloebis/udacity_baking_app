package udacity.winni.bakingapp.domain.repository;

import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;

import io.reactivex.Observable;
import udacity.winni.bakingapp.BakingApplication;
import udacity.winni.bakingapp.domain.usecase.UseCase;

/**
 * Created by winniseptiani on 7/19/17.
 */

public class GetFavoriteRecipes extends UseCase {

    private RecipeRepository recipeRepository;

    public GetFavoriteRecipes(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return recipeRepository.getFavoriteRecipes();
    }
}