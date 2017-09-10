package udacity.winni.bakingapp.dev;


import java.util.List;

import udacity.winni.bakingapp.base.BasePresenter;
import udacity.winni.bakingapp.base.BaseView;
import udacity.winni.bakingapp.presentation.model.RecipeVM;

/**
 * Created by winniseptiani on 6/16/17.
 */

public interface WidgetContract {

    interface WidgetListener {

        void onGetFavoriteRecipesSuccess(List<RecipeVM> recipes);

        void onGetFavoriteRecipesFailed();
    }

    interface Presenter extends BasePresenter {

        void getFavoritRecipes();
    }
}
