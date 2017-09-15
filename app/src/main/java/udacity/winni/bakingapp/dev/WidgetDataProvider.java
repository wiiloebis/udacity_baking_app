package udacity.winni.bakingapp.dev;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import java.util.ArrayList;
import java.util.List;

import udacity.winni.bakingapp.R;
import udacity.winni.bakingapp.presentation.model.IngredientVM;
import udacity.winni.bakingapp.presentation.model.RecipeVM;
import udacity.winni.bakingapp.presentation.recipedetail.RecipeDetailActivity;
import udacity.winni.bakingapp.presentation.recipegallery.RecipeGalleryPresenter;

import static udacity.winni.bakingapp.presentation.recipegallery.RecipeGalleryPresenter
    .BAKING_APP_PREFERENCE;

@SuppressLint("NewApi")
public class WidgetDataProvider implements RemoteViewsFactory {

    List<IngredientVM> mCollections = new ArrayList<>();

    RecipeVM recipeVM;

    Context mContext = null;

    private SharedPreferences sharedpreferences;

    public WidgetDataProvider(Context context) {
        mContext = context;
        sharedpreferences = context
            .getSharedPreferences(BAKING_APP_PREFERENCE, Context.MODE_PRIVATE);
    }

    @Override
    public int getCount() {
        return mCollections.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews mView = new RemoteViews(mContext.getPackageName(),
            android.R.layout.simple_list_item_1);

        RemoteViews mLayout = new RemoteViews(mContext.getPackageName(),
            R.layout.baking_app_widget);

        mLayout.setTextViewText(R.id.textview_recipe_name, recipeVM.getName());

        IngredientVM ingredientVM = mCollections.get(position);
        mView.setTextViewText(android.R.id.text1,
            ingredientVM.getIngredient() + "\n" + ingredientVM.getQuantity() + " " + ingredientVM
                .getMeasure());
        mView.setTextColor(android.R.id.text1, Color.BLACK);

        final Intent fillInIntent = new Intent();
        final Bundle bundle = new Bundle();
        bundle.putParcelable(RecipeDetailActivity.RECIPES, recipeVM);
        fillInIntent.putExtras(bundle);
        mView.setOnClickFillInIntent(android.R.id.text1, fillInIntent);
        return mView;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onCreate() {
        initData();
    }

    @Override
    public void onDataSetChanged() {
        initData();
    }

    private void initData() {
        Gson gson = new Gson();
        String jsonString = sharedpreferences.getString(RecipeGalleryPresenter.RECIPE_KEY, "");
        recipeVM = gson.fromJson(jsonString, RecipeVM.class);
        if (recipeVM != null) {
            mCollections = recipeVM.getIngredients();
        }
    }

    @Override
    public void onDestroy() {
    }
}
