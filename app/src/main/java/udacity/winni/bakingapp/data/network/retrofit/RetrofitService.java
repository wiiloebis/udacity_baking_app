package udacity.winni.bakingapp.data.network.retrofit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import udacity.winni.bakingapp.data.model.Recipe;
import udacity.winni.bakingapp.data.network.response.GetRecipesResponse;

/**
 * Created by winniseptiani on 8/2/17.
 */

public interface RetrofitService {

    String CONTENT_TYPE_JSON = "Content-Type: application/vnd.api+json";

    @Headers({CONTENT_TYPE_JSON})
    @GET("59121517_baking/baking.json")
    Observable<List<Recipe>> getRecipes();
}
