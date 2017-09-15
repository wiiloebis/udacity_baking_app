package udacity.winni.bakingapp.dev;

import com.google.gson.Gson;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import udacity.winni.bakingapp.R;
import udacity.winni.bakingapp.presentation.model.RecipeVM;
import udacity.winni.bakingapp.presentation.recipegallery.RecipeGalleryPresenter;

import static udacity.winni.bakingapp.presentation.recipegallery.RecipeGalleryPresenter
    .BAKING_APP_PREFERENCE;
import static udacity.winni.bakingapp.presentation.recipegallery.RecipeGalleryPresenter.RECIPE_KEY;

/**
 * Created by winniseptiani on 13/9/17.
 */

public class BakingAppService extends IntentService {

    public static final String EXTRA_RECIPE = "udacity.winni.bakingapp.extra.recipe";

    public static final String ACTION_UPDATE_INGREDIENTS_WIDGETS = "udacity.winni.bakingapp" +
        ".action.update_ingredients_widgets";

    public static final String ACTION_SAVE_RECIPE = "udacity.winni.bakingapp.action.save_recipe";

    public BakingAppService() {
        super("BakingAppService");
    }

    public static void startActionAddRecipe(Context context, RecipeVM recipe) {
        Intent intent = new Intent(context, BakingAppService.class);
        intent.setAction(ACTION_SAVE_RECIPE);
        intent.putExtra(EXTRA_RECIPE, recipe);
        context.startService(intent);
    }

    public static void startActionUpdateBakingAppWidgets(Context context) {
        Intent intent = new Intent(context, BakingAppService.class);
        intent.setAction(ACTION_UPDATE_INGREDIENTS_WIDGETS);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SAVE_RECIPE.equals(action)) {
                RecipeVM recipeVM = intent.getParcelableExtra(EXTRA_RECIPE);
                handleActionSaveRecipe(recipeVM);
            } else if (ACTION_UPDATE_INGREDIENTS_WIDGETS.equals(action)) {
                handleActionUpdatePlantWidgets();
            }
        }
    }

    private void handleActionSaveRecipe(RecipeVM recipe) {
        if (recipe != null) {
            SharedPreferences sharedpreferences = this
                .getSharedPreferences(BAKING_APP_PREFERENCE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            Gson gson = new Gson();
            String jsonString = gson.toJson(recipe);
            editor.putString(RECIPE_KEY, jsonString);
            editor.commit();
        }
        startActionUpdateBakingAppWidgets(this);
    }

    private void handleActionUpdatePlantWidgets() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager
            .getAppWidgetIds(new ComponentName(this, BakingAppWidget.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widgetCollectionList);

        SharedPreferences sharedpreferences = this
            .getSharedPreferences(BAKING_APP_PREFERENCE, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonString = sharedpreferences.getString(RecipeGalleryPresenter.RECIPE_KEY, "");
        RecipeVM recipe = gson.fromJson(jsonString, RecipeVM.class);

        BakingAppWidget
            .updateBakingAppWidgets(this, appWidgetManager, appWidgetIds, recipe);
    }
}
