package udacity.winni.bakingapp.presentation.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import udacity.winni.bakingapp.data.model.Ingredient;
import udacity.winni.bakingapp.presentation.model.IngredientVM;

/**
 * Created by winniseptiani on 7/8/17.
 */

public class IngredientMapper {

    public static IngredientVM transform(Ingredient ingredient) {
        IngredientVM ingredientVM = null;

        if (ingredient != null) {
            ingredientVM = new IngredientVM();
            ingredientVM.setIngredient(ingredient.getIngredient());
            ingredientVM.setMeasure(ingredient.getMeasure());
            ingredientVM.setQuantity(ingredient.getQuantity());
        }

        return ingredientVM;
    }

    public static Ingredient transform(IngredientVM ingredientVM) {
        Ingredient ingredient = null;

        if (ingredient != null) {
            ingredient = new Ingredient();
            ingredient.setIngredient(ingredientVM.getIngredient());
            ingredient.setMeasure(ingredientVM.getMeasure());
            ingredient.setQuantity(ingredientVM.getQuantity());
        }

        return ingredient;
    }

    public static List<IngredientVM> transform(List<Ingredient> ingredients) {
        List<IngredientVM> ingredientVMs = null;

        if (ingredients != null) {
            ingredientVMs = new ArrayList<>();

            for (Ingredient ingredient : ingredients) {
                ingredientVMs.add(transform(ingredient));
            }
        } else {
            ingredientVMs = Collections.emptyList();
        }

        return ingredientVMs;
    }

    public static List<Ingredient> transformToIngredients(List<IngredientVM> ingredientVMs) {
        List<Ingredient> ingredients = null;

        if (ingredientVMs != null) {
            ingredients = new ArrayList<>();

            for (IngredientVM ingredientVM : ingredientVMs) {
                ingredients.add(transform(ingredientVM));
            }
        } else {
            ingredientVMs = Collections.emptyList();
        }

        return ingredients;
    }

}
