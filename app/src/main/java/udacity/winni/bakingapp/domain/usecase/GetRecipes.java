package udacity.winni.bakingapp.domain.usecase;

import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;

import io.reactivex.Observable;
import udacity.winni.bakingapp.BakingApplication;
import udacity.winni.bakingapp.domain.repository.RecipeRepository;

/**
 * Created by winniseptiani on 6/26/17.
 */

public class GetRecipes extends UseCase {

    private RecipeRepository recipeRepository;

    public GetRecipes(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return recipeRepository.getRecipes();
    }
}