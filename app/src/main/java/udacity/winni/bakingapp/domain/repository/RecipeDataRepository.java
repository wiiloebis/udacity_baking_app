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

    public RecipeDataRepository(RecipeDataSource cloudRecipeDataSource) {
        this.cloudRecipeDataSource = cloudRecipeDataSource;
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {
        return cloudRecipeDataSource.getRecipes();
    }
}
