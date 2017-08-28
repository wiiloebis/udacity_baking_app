package udacity.winni.bakingapp.data;

import java.util.List;

import io.reactivex.Observable;
import udacity.winni.bakingapp.BuildConfig;
import udacity.winni.bakingapp.data.model.Recipe;

/**
 * Created by winniseptiani on 6/21/17.
 */

public interface RestApi {

    String SERVER_URL = BuildConfig.SERVER_URL;

    int CONNECTION_TIMEOUT = 60;

    int READ_TIMEOUT = 60;

    Observable<List<Recipe>> getRecipes();
}
