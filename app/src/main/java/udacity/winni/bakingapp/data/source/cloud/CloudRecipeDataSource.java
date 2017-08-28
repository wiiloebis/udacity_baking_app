package udacity.winni.bakingapp.data.source.cloud;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import udacity.winni.bakingapp.data.RestApi;
import udacity.winni.bakingapp.data.model.Recipe;
import udacity.winni.bakingapp.data.source.RecipeDataSource;

/**
 * Created by winniseptiani on 6/26/17.
 */

public class CloudRecipeDataSource implements RecipeDataSource {

    private final RestApi restApi;

    public CloudRecipeDataSource(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {
        return restApi.getRecipes();
    }
}
