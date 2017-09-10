package udacity.winni.bakingapp.data.source.factory;

import udacity.winni.bakingapp.data.RestApi;
import udacity.winni.bakingapp.data.network.retrofit.RetrofitRestApiImpl;
import udacity.winni.bakingapp.data.source.RecipeDataSource;
import udacity.winni.bakingapp.data.source.cloud.CloudRecipeDataSource;

/**
 * Created by winniseptiani on 6/26/17.
 */

public class RecipeDataSourceFactory {

    public RecipeDataSource createCloudDataSource() {
        RestApi restApi = new RetrofitRestApiImpl();
        return new CloudRecipeDataSource(restApi);
    }
}
