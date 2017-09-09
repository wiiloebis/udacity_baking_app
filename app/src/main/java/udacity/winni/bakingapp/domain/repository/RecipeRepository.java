package udacity.winni.bakingapp.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import udacity.winni.bakingapp.data.model.Recipe;

/**
 * Created by winniseptiani on 6/26/17.
 */

public interface RecipeRepository {

    Observable<List<Recipe>> getRecipes();

    Observable<List<Recipe>> getFavoriteRecipes();

    Observable<Boolean> addFavoriteRecipe(Recipe recipe);
}
