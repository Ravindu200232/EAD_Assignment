package lk.sliit.solargrid.data.remote;

public interface ApiCallback<T> {

    /** The request worked; the result is what the API sent back. */
    void onSuccess(T result);

    /** The request failed; the error already holds a readable message. */
    void onError(ApiError error);
}
