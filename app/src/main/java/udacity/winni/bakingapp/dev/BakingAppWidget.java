package udacity.winni.bakingapp.dev;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

import udacity.winni.bakingapp.R;
import udacity.winni.bakingapp.presentation.model.RecipeVM;
import udacity.winni.bakingapp.presentation.recipedetail.RecipeDetailActivity;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidget extends AppWidgetProvider {

    public static void updateBakingAppWidgets(Context context, AppWidgetManager appWidgetManager,
        int[] appWidgetIds, RecipeVM recipe) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, recipe);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
        int appWidgetId, RecipeVM recipe) {

        RemoteViews mView = new RemoteViews(context.getPackageName(),
            R.layout.baking_app_widget);

        Intent intent = new Intent(context, WidgetService.class);
        mView.setTextViewText(R.id.textview_recipe_name, recipe.getName());
        mView.setRemoteAdapter(appWidgetId, R.id.widgetCollectionList, intent);
        Intent appIntent = new Intent(context, RecipeDetailActivity.class);
        appIntent.putExtra(RecipeDetailActivity.RECIPES, recipe);
        PendingIntent appPendingIntent = PendingIntent
            .getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mView.setOnClickPendingIntent(R.id.textview_recipe_name, appPendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, mView);
    }

    @SuppressLint("NewApi")
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
        int[] appWidgetIds) {
        BakingAppService.startActionUpdateBakingAppWidgets(context);
    }
}

