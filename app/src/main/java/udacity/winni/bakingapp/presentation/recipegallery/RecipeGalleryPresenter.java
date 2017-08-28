package udacity.winni.bakingapp.presentation.recipegallery;

import java.util.List;

import io.reactivex.observers.DisposableObserver;
import udacity.winni.bakingapp.data.model.Recipe;
import udacity.winni.bakingapp.domain.usecase.GetRecipes;
import udacity.winni.bakingapp.presentation.mapper.RecipeMapper;
import udacity.winni.bakingapp.presentation.model.RecipeVM;

/**
 * Created by winniseptiani on 6/16/17.
 */

public class RecipeGalleryPresenter implements RecipeGalleryContract.Presenter {

    private RecipeGalleryContract.View view;

    private GetRecipes getRecipes;

    private RecipeMapper recipeMapper;

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
