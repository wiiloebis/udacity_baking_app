package udacity.winni.bakingapp.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.Nullable;

/**
 * Created by winniseptiani on 7/18/17.
 */

public interface LocalApi {

    Cursor getFavoriteRecipes(@Nullable String[] projection,
        @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder);

    long insertFavoriteRecipe(ContentValues contentValues);
}
