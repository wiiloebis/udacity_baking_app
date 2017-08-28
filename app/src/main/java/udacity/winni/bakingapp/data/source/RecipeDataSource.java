package udacity.winni.bakingapp.data.source;

import java.util.List;

import io.reactivex.Observable;
import udacity.winni.bakingapp.data.model.Recipe;

/**
 * Created by winniseptiani on 6/26/17.
 */

public interface RecipeDataSource {

    Observable<List<Recipe>> getRecipes();
}
