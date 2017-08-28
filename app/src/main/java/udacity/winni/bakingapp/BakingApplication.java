package udacity.winni.bakingapp;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * Created by winniseptiani on 8/2/17.
 */

public class BakingApplication extends MultiDexApplication {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getAppContext() {
        return BakingApplication.context;
    }
}
