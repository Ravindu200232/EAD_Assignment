package lk.sliit.solargrid.data.repo;

import androidx.annotation.Nullable;

import lk.sliit.solargrid.data.remote.ApiError;

public interface CachedCallback<T> {

    /**
     * The data to show. The reason is null when it came from the API, and
     * says why the API could not be reached when it came from the phone.
     */
    void onResult(T value, @Nullable ApiError offlineReason);

    /** Nothing could be shown: the call failed and there is no saved copy. */
    void onError(ApiError error);
}
