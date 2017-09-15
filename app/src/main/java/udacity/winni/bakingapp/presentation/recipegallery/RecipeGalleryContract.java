package udacity.winni.bakingapp.presentation.recipegallery;


import android.content.Context;

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

        void showLoadingBar();

        void hideLoadingBar();

        Context getContext();
    }

    interface Presenter extends BasePresenter {

        void getRecipes();

        void saveSelectedRecipe(RecipeVM recipe);
    }
}
