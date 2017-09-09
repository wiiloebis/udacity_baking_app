package udacity.winni.bakingapp.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

/**
 * Created by winniseptiani on 7/17/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "movieDb";

    private RecipeContract recipeContract;

    public DatabaseHelper(Context context, RecipeContract recipeContract) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.recipeContract = recipeContract;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECIPE_TABLE = "CREATE TABLE " + RecipeContract.RecipeEntry.TABLE_RECIPE + "("
            + RecipeContract.RecipeEntry.COLUMN_ID + " TEXT PRIMARY KEY," + RecipeContract
            .RecipeEntry.COLUMN_CONTENT
            + " TEXT)";
        db.execSQL(CREATE_RECIPE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(
            "ALTER TABLE " + RecipeContract.RecipeEntry.TABLE_RECIPE + " ADD COLUMN " +
                RecipeContract.RecipeEntry.COLUMN_OTHER + " string;");
    }

    public Cursor getFavoriteRecipes(@Nullable String[] projection,
        @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return recipeContract
            .getFavoriteRecipes(getWritableDatabase(), projection, selection, selectionArgs,
                sortOrder);
    }

    public long addFavoriteRecipe(ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();
        return recipeContract.addFavoriteMovie(contentValues, db);
    }
}
