package lk.sliit.solargrid;

import android.app.Application;

public class SolarGridApp extends Application {

    /** Prepares the shared parts of the app before the first screen opens. */
    @Override
    public void onCreate() {
        super.onCreate();
        AppContainer.init(this);
    }
}
