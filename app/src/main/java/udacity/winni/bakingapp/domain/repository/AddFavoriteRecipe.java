package udacity.winni.bakingapp.domain.repository;

import com.google.gson.Gson;

import io.reactivex.Observable;
import udacity.winni.bakingapp.data.model.Recipe;
import udacity.winni.bakingapp.domain.usecase.UseCase;

/**
 * Created by winniseptiani on 7/19/17.
 */

public class AddFavoriteRecipe extends UseCase {

    private RecipeRepository recipeRepository;

    private Recipe recipe;

    public AddFavoriteRecipe(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public void setRecipeData(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return recipeRepository.addFavoriteRecipe(recipe);
    }
}
