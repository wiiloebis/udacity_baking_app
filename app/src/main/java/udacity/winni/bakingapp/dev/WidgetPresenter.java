package udacity.winni.bakingapp.dev;

import java.util.List;

import io.reactivex.observers.DisposableObserver;
import udacity.winni.bakingapp.data.model.Recipe;
import udacity.winni.bakingapp.domain.repository.AddFavoriteRecipe;
import udacity.winni.bakingapp.domain.repository.GetFavoriteRecipes;
import udacity.winni.bakingapp.domain.usecase.GetRecipes;
import udacity.winni.bakingapp.presentation.mapper.IngredientMapper;
import udacity.winni.bakingapp.presentation.mapper.RecipeMapper;
import udacity.winni.bakingapp.presentation.mapper.StepMapper;
import udacity.winni.bakingapp.presentation.model.RecipeVM;
import udacity.winni.bakingapp.presentation.recipegallery.RecipeGalleryContract;

/**
 * Created by winniseptiani on 6/16/17.
 */

public class WidgetPresenter implements WidgetContract.Presenter {

    private GetFavoriteRecipes getFavoriteRecipes;

    private RecipeMapper recipeMapper;

    private WidgetContract.WidgetListener listener;

    public WidgetPresenter(GetFavoriteRecipes getFavoriteRecipes,
        RecipeMapper recipeMapper) {
        this.getFavoriteRecipes = getFavoriteRecipes;
        this.recipeMapper = recipeMapper;
    }

    public void setListener(WidgetContract.WidgetListener listener) {
        this.listener = listener;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getFavoriteRecipes.unsubscribe();
    }

    @Override
    public void getFavoritRecipes() {
        getFavoriteRecipes.execute(new DisposableObserver<List<Recipe>>() {

            @Override
            public void onNext(List<Recipe> recipes) {
                List<RecipeVM> recipeVms = recipeMapper.transform(recipes);
                if (recipeVms != null) {
                    listener.onGetFavoriteRecipesSuccess(recipeVms);
                } else {
                    listener.onGetFavoriteRecipesFailed();
                }
            }

            @Override
            public void onError(Throwable e) {
                listener.onGetFavoriteRecipesFailed();
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
