package udacity.winni.bakingapp.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import udacity.winni.bakingapp.data.model.Recipe;
import udacity.winni.bakingapp.data.source.RecipeDataSource;

/**
 * Created by winniseptiani on 6/26/17.
 */

public class RecipeDataRepository implements RecipeRepository {

    private RecipeDataSource cloudRecipeDataSource;

    private RecipeDataSource localRecipeDataSource;

    public RecipeDataRepository(RecipeDataSource cloudRecipeDataSource,
        RecipeDataSource localRecipeDataSource) {
        this.cloudRecipeDataSource = cloudRecipeDataSource;
        this.localRecipeDataSource = localRecipeDataSource;
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {
        return cloudRecipeDataSource.getRecipes();
    }

    @Override
    public Observable<List<Recipe>> getFavoriteRecipes() {
        return localRecipeDataSource.getFavoriteRecipe();
    }

    @Override
    public Observable<Boolean> addFavoriteRecipe(Recipe recipe) {
        return localRecipeDataSource.addFavoriteRecipe(recipe);
    }
}
