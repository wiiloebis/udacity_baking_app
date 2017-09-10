package udacity.winni.bakingapp.presentation.recipegallery;

import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import io.reactivex.observers.DisposableObserver;
import udacity.winni.bakingapp.data.model.Recipe;
import udacity.winni.bakingapp.domain.repository.AddFavoriteRecipe;
import udacity.winni.bakingapp.domain.usecase.GetRecipes;
import udacity.winni.bakingapp.presentation.mapper.IngredientMapper;
import udacity.winni.bakingapp.presentation.mapper.RecipeMapper;
import udacity.winni.bakingapp.presentation.mapper.StepMapper;
import udacity.winni.bakingapp.presentation.model.RecipeVM;

/**
 * Created by winniseptiani on 6/16/17.
 */

public class RecipeGalleryPresenter implements RecipeGalleryContract.Presenter {

    private RecipeGalleryContract.View view;

    private GetRecipes getRecipes;

    private AddFavoriteRecipe addFavoriteRecipe;

    private RecipeMapper recipeMapper;


    public static final String BAKING_APP_PREFERENCE = "BAKING_APP_PREFERENCE";

    public static final String RECIPE_KEY = "RECIPE_KEY";

    private SharedPreferences sharedpreferences;

    public RecipeGalleryPresenter(RecipeGalleryContract.View view,
        GetRecipes getRecipes,
        AddFavoriteRecipe addFavoriteRecipe,
        RecipeMapper recipeMapper) {
        this.view = view;
        this.getRecipes = getRecipes;
        this.addFavoriteRecipe = addFavoriteRecipe;
        this.recipeMapper = recipeMapper;
    }

    @Override
    public void getRecipes() {
        view.showLoadingBar();
        getRecipes.execute(new DisposableObserver<List<Recipe>>() {

            @Override
            public void onNext(List<Recipe> recipes) {
                List<RecipeVM> recipeVms = recipeMapper.transform(recipes);

                sharedpreferences = view.getContext()
                    .getSharedPreferences(BAKING_APP_PREFERENCE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                Gson gson = new Gson();
                String jsonString = gson.toJson(recipes);
                editor.putString(RECIPE_KEY, jsonString);
                editor.commit();

                view.hideLoadingBar();
                if (recipeVms != null) {
                    view.onGetRecipeSuccess(recipeVms);
                } else {
                    view.onGetRecipeFailed();
                }
            }

            @Override
            public void onError(Throwable e) {
                view.onGetRecipeFailed();
                view.hideLoadingBar();
            }

            @Override
            public void onComplete() {
                view.hideLoadingBar();
            }
        });
    }

    @Override
    public void addAsFavoriteRecipe(RecipeVM recipeVM) {
        view.showLoadingBar();
        Recipe recipe = new Recipe();
        recipe.setId(recipeVM.getId());
        recipe.setImage(recipeVM.getImage());
        recipe.setIngredients(IngredientMapper.transformToIngredients(recipeVM.getIngredients()));
        recipe.setName(recipeVM.getName());
        recipe.setServings(recipeVM.getServings());
        recipe.setSteps(StepMapper.transformToSteps(recipeVM.getSteps()));
        addFavoriteRecipe.setRecipeData(recipe);
        addFavoriteRecipe.execute(new DisposableObserver<Boolean>() {

            @Override
            public void onNext(Boolean success) {
                view.onAddFavoriteRecipeSuccess();
            }

            @Override
            public void onError(Throwable e) {
                view.onAddFavoriteRecipeFailed();
                view.hideLoadingBar();
            }

            @Override
            public void onComplete() {
                view.hideLoadingBar();
            }
        });
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getRecipes.unsubscribe();
    }
}