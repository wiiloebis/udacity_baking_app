package udacity.winni.bakingapp.dev;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.RemoteViewsService;

import java.util.List;

import udacity.winni.bakingapp.ApplicationComponent;
import udacity.winni.bakingapp.domain.repository.GetFavoriteRecipes;
import udacity.winni.bakingapp.presentation.mapper.RecipeMapper;
import udacity.winni.bakingapp.presentation.model.RecipeVM;
import udacity.winni.bakingapp.presentation.recipegallery.RecipeGalleryPresenter;

@SuppressLint("NewApi")
public class WidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

//        WidgetPresenter widgetPresenter = new WidgetPresenter(
//            ApplicationComponent.provideGetFavoriteRecipes(), new RecipeMapper());

        WidgetDataProvider dataProvider = new WidgetDataProvider(
            getApplicationContext());
        return dataProvider;
    }
}
