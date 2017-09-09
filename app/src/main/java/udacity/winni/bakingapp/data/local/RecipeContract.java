package udacity.winni.bakingapp.data.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;

import java.util.concurrent.Callable;

/**
 * Created by winniseptiani on 7/18/17.
 */

public class RecipeContract {

    public Cursor getFavoriteRecipes(SQLiteDatabase db, @Nullable String[] projection,
        @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor = db.query(RecipeEntry.TABLE_RECIPE,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder);

        return cursor;
    }

    public long addFavoriteMovie(ContentValues contentValues,
        SQLiteDatabase db) {
        long rowId = 0;
        try {
            db.beginTransaction();
            rowId = db.insert(RecipeEntry.TABLE_RECIPE, null, contentValues);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            return 0L;
        } finally {
            db.endTransaction();
        }
        return rowId;
    }


    public static final String AUTHORITY = "udacity.winni.bakingapp";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_FAVORITE_RECIPES = "favoriterecipes";

    public static class RecipeEntry implements BaseColumns {

        public static final String TABLE_RECIPE = "recipe";

        public static final String COLUMN_ID = "id";

        public static final String COLUMN_CONTENT = "content";

        public static final String COLUMN_OTHER = "other";

        public static final Uri CONTENT_URI =
            BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITE_RECIPES).build();

    }
}
