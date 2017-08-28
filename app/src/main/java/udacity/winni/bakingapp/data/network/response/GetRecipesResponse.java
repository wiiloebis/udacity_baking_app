package udacity.winni.bakingapp.data.network.response;

import java.util.List;

import udacity.winni.bakingapp.data.model.Recipe;

/**
 * Created by winniseptiani on 6/23/17.
 */

public class GetRecipesResponse {

    private List<Recipe> recipes;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
