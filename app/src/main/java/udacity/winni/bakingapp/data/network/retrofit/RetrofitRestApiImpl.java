package udacity.winni.bakingapp.data.network.retrofit;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import udacity.winni.bakingapp.BakingApplication;
import udacity.winni.bakingapp.BuildConfig;
import udacity.winni.bakingapp.data.RestApi;
import udacity.winni.bakingapp.data.model.Recipe;

/**
 * Created by winniseptiani on 6/21/17.
 */

public class RetrofitRestApiImpl implements RestApi {

    private final static int CACHE_SIZE_BYTES = 1024 * 1024 * 2;

    private RetrofitService apiService;

    public RetrofitRestApiImpl() {
        buildRetrofit();
    }

    private void buildRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(RestApi.SERVER_URL)
            .client(createHttpClient(false))
            .build();

        apiService = retrofit.create(RetrofitService.class);
    }

    private OkHttpClient createHttpClient(boolean cacheable) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                .method(original.method(), original.body())
                .build();
            return chain.proceed(request);
        });

        if (cacheable) {
            setCache(httpClient);
        }

        setTimeout(httpClient);

        setLogger(httpClient);

        return httpClient.build();
    }

    private void setCache(OkHttpClient.Builder okHttpClientBuilder) {
        okHttpClientBuilder.cache(
            new Cache(BakingApplication.getAppContext().getCacheDir(), CACHE_SIZE_BYTES));
    }

    private void setTimeout(OkHttpClient.Builder okHttpClientBuilder) {
        okHttpClientBuilder.readTimeout(RestApi.READ_TIMEOUT, TimeUnit.SECONDS);
        okHttpClientBuilder.connectTimeout(RestApi.CONNECTION_TIMEOUT, TimeUnit.SECONDS);
    }

    private void setLogger(OkHttpClient.Builder okHttpClientBuilder) {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(logging);
        }
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {
        return apiService.getRecipes()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io());
    }
}
