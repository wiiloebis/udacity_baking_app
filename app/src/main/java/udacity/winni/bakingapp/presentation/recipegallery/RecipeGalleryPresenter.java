package udacity.winni.bakingapp.presentation.recipegallery;

import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import io.reactivex.observers.DisposableObserver;
import udacity.winni.bakingapp.data.model.Recipe;
import udacity.winni.bakingapp.dev.BakingAppService;
import udacity.winni.bakingapp.domain.usecase.GetRecipes;
import udacity.winni.bakingapp.presentation.mapper.RecipeMapper;
import udacity.winni.bakingapp.presentation.model.RecipeVM;

/**
 * Created by winniseptiani on 6/16/17.
 */

public class RecipeGalleryPresenter implements RecipeGalleryContract.Presenter {

    private static final int FIRST_INDEX = 0;

    public static final String BAKING_APP_PREFERENCE = "BAKING_APP_PREFERENCE";

    public static final String RECIPE_POSITION = "RECIPE_POSITION";

    public static final String RECIPE_KEY = "RECIPE_KEY";

    private RecipeGalleryContract.View view;

    private GetRecipes getRecipes;

    private RecipeMapper recipeMapper;

    private SharedPreferences sharedpreferences;

    public RecipeGalleryPresenter(RecipeGalleryContract.View view,
        GetRecipes getRecipes,
        RecipeMapper recipeMapper) {
        this.view = view;
        this.getRecipes = getRecipes;
        this.recipeMapper = recipeMapper;
    }

    @Override
    public void getRecipes() {
        view.showLoadingBar();
        getRecipes.execute(new DisposableObserver<List<Recipe>>() {

            @Override
            public void onNext(List<Recipe> recipes) {
                List<RecipeVM> recipeVms = recipeMapper.transform(recipes);

                RecipeVM recipeVM = recipeVms.get(FIRST_INDEX);

                if (recipeVM != null) {
                    sharedpreferences = view.getContext()
                        .getSharedPreferences(BAKING_APP_PREFERENCE, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(recipeVM);
                    editor.putString(RECIPE_KEY, jsonString);
                    editor.commit();
                }

                int position = sharedpreferences.getInt(RECIPE_POSITION, FIRST_INDEX);

                view.hideLoadingBar();
                if (recipeVms != null) {
                    view.onGetRecipeSuccess(recipeVms, position);
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
    public void saveSelectedRecipe(RecipeVM recipe, int position) {
        SharedPreferences sharedpreferences = view.getContext()
            .getSharedPreferences(BAKING_APP_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(RECIPE_POSITION, position);
        editor.commit();
        BakingAppService.startActionAddRecipe(view.getContext(), recipe);
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