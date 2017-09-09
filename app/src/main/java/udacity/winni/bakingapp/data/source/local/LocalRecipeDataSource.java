package udacity.winni.bakingapp.data.source.local;

import com.google.gson.Gson;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import udacity.winni.bakingapp.BakingApplication;
import udacity.winni.bakingapp.data.local.RecipeContract;
import udacity.winni.bakingapp.data.model.Recipe;
import udacity.winni.bakingapp.data.source.RecipeDataSource;

/**
 * Created by winniseptiani on 7/18/17.
 */

public class LocalRecipeDataSource implements RecipeDataSource {

    private static <T> Observable<T> makeObservable(final Callable<T> func) {

        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(func.call());
                    emitter.onComplete();
                } catch (Exception ex) {
                    emitter.onError(ex);
                }

            }
        });
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {
        return null;
    }

    @Override
    public Observable<List<Recipe>> getFavoriteRecipe() {
        return makeObservable(new Callable<List<Recipe>>() {
            @Override
            public List<Recipe> call() {
                try {
                    Cursor cursor = BakingApplication.getAppContext().getContentResolver().query(
                        RecipeContract.RecipeEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);

                    List<Recipe> recipes = new ArrayList<>();
                    Gson gson = new Gson();

                    while (cursor.moveToNext()) {
                        int id = cursor
                            .getInt(cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_ID));
                        String content = cursor.getString(cursor.getColumnIndex(
                            RecipeContract.RecipeEntry
                                .COLUMN_CONTENT));

                        Recipe recipe = gson.fromJson(content, Recipe.class);
                        recipes.add(recipe);

                    }
                    return recipes;

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }

    @Override
    public Observable<Boolean> addFavoriteRecipe(Recipe recipe) {
        return makeObservable(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                Gson gson = new Gson();
                String jsonString = gson.toJson(recipe);
                ContentValues cv = new ContentValues();
                cv.put(RecipeContract.RecipeEntry.COLUMN_ID, recipe.getId());
                cv.put(RecipeContract.RecipeEntry.COLUMN_CONTENT, jsonString);
                BakingApplication.getAppContext().getContentResolver()
                    .insert(RecipeContract.RecipeEntry.CONTENT_URI, cv);
                return true;
            }
        });
    }
}
