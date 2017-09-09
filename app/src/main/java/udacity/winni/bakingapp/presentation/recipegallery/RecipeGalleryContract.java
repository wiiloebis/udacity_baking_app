package udacity.winni.bakingapp.presentation.recipegallery;


import java.util.List;

import udacity.winni.bakingapp.base.BasePresenter;
import udacity.winni.bakingapp.base.BaseView;
import udacity.winni.bakingapp.presentation.model.RecipeVM;

/**
 * Created by winniseptiani on 6/16/17.
 */

public interface RecipeGalleryContract {

    interface View extends BaseView {

        void onGetRecipeSuccess(List<RecipeVM> recipes);

        void onGetRecipeFailed();

        void onAddFavoriteRecipeSuccess();

        void onAddFavoriteRecipeFailed();

        void showLoadingBar();

        void hideLoadingBar();
    }

    interface Presenter extends BasePresenter {

        void getRecipes();

        void addAsFavoriteRecipe(RecipeVM recipeVM);
    }
}
