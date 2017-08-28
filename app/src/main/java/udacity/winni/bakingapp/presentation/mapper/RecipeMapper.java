package udacity.winni.bakingapp.presentation.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import udacity.winni.bakingapp.data.model.Ingredient;
import udacity.winni.bakingapp.data.model.Recipe;
import udacity.winni.bakingapp.data.model.Step;
import udacity.winni.bakingapp.presentation.model.RecipeVM;

/**
 * Created by winniseptiani on 8/2/17.
 */

public class RecipeMapper {

    public static RecipeVM transform(Recipe recipe) {
        RecipeVM recipeVM = null;

        if (recipe != null) {
            recipeVM = new RecipeVM();
            recipeVM.setId(recipe.getId());
            recipeVM.setName(recipe.getName());
            recipeVM.setImage(recipe.getImage());
            recipeVM.setIngredients(IngredientMapper.transform(recipe.getIngredients()));
            recipeVM.setServings(recipe.getServings());
            recipeVM.setSteps(StepMapper.transform(recipe.getSteps()));
        }

        return recipeVM;
    }

    public static List<RecipeVM> transform(List<Recipe> recipes) {
        List<RecipeVM> recipeVMs = null;

        if (recipes != null) {
            recipeVMs = new ArrayList<>();

            for (Recipe recipe : recipes) {
                recipeVMs.add(transform(recipe));
            }
        } else {
            recipeVMs = Collections.emptyList();
        }

        return recipeVMs;
    }
}
