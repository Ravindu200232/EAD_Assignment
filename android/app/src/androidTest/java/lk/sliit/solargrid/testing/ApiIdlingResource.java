package lk.sliit.solargrid.testing;

import androidx.test.espresso.IdlingResource;

import lk.sliit.solargrid.util.Idling;

public class ApiIdlingResource implements IdlingResource {

    private ResourceCallback callback;

    /** The name Espresso prints when a test times out. */
    @Override
    public String getName() {
        return "SolarGrid API calls";
    }

    /** True when no request is waiting for an answer. */
    @Override
    public boolean isIdleNow() {
        boolean idle = Idling.isIdle();
        if (idle && callback != null) {
            callback.onTransitionToIdle();
        }
        return idle;
    }

    /** Espresso gives us a way to say "the app is free again". */
    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.callback = callback;
    }
}
