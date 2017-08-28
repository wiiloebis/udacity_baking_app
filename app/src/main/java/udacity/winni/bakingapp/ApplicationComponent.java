package udacity.winni.bakingapp;

import udacity.winni.bakingapp.data.source.factory.RecipeDataSourceFactory;
import udacity.winni.bakingapp.domain.repository.RecipeDataRepository;
import udacity.winni.bakingapp.domain.repository.RecipeRepository;
import udacity.winni.bakingapp.domain.usecase.GetRecipes;

/**
 * Created by winniseptiani on 6/27/17.
 */

public class ApplicationComponent {

    private static RecipeRepository provideRecipeRepository() {
        RecipeDataSourceFactory recipeDataSourceFactory = new
            RecipeDataSourceFactory();
        return new RecipeDataRepository(recipeDataSourceFactory.createCloudDataSource());
    }

    public static GetRecipes provideGetRecipes() {
        return new GetRecipes(provideRecipeRepository());
    }
}
