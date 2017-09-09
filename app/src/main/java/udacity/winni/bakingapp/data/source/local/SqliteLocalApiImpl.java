package udacity.winni.bakingapp.data.source.local;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.Nullable;

import udacity.winni.bakingapp.data.LocalApi;
import udacity.winni.bakingapp.data.local.DatabaseHelper;
import udacity.winni.bakingapp.data.local.RecipeContract;


/**
 * Created by winniseptiani on 7/18/17.
 */

public class SqliteLocalApiImpl implements LocalApi {

    private DatabaseHelper dbHelper;

    public SqliteLocalApiImpl(Context context) {
        dbHelper = new DatabaseHelper(context, new RecipeContract());
    }

    @Override
    public Cursor getFavoriteRecipes(@Nullable String[] projection, @Nullable String selection,
        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return dbHelper.getFavoriteRecipes(projection, selection, selectionArgs, sortOrder);
    }

    @Override
    public long insertFavoriteRecipe(ContentValues contentValues) {
        return dbHelper.addFavoriteRecipe(contentValues);
    }
}
