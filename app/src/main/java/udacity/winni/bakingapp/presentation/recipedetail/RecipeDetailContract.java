package udacity.winni.bakingapp.presentation.recipedetail;

import udacity.winni.bakingapp.base.BasePresenter;
import udacity.winni.bakingapp.base.BaseView;
import udacity.winni.bakingapp.presentation.model.RecipeVM;

/**
 * Created by winniseptiani on 6/16/17.
 */

public interface RecipeDetailContract {

    interface View extends BaseView {

        void onGetRecipeDetailSuccess(RecipeVM recipeVM);

        void onGetRecipeDetailFailed();
    }

    interface Presenter extends BasePresenter {

        void getRecipeDetail();
    }
}
