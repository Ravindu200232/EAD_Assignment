package lk.sliit.solargrid.data.remote;

import androidx.annotation.NonNull;

import java.io.IOException;

import lk.sliit.solargrid.session.SessionStore;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private final SessionStore sessions;

    /** Needs the store that holds the token. */
    public AuthInterceptor(SessionStore sessions) {
        this.sessions = sessions;
    }

    /** Runs for every request the app makes. */
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        String token = sessions.token();

        Request.Builder builder = chain.request().newBuilder()
                .header("Accept", "application/json");
        if (token != null) {
            builder.header("Authorization", "Bearer " + token);
        }

        Response response = chain.proceed(builder.build());

        if (response.code() == 401 && token != null) {
            sessions.endSession(ApiError.SESSION_ENDED);
        }
        return response;
    }
}
