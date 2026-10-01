package lk.sliit.solargrid.data.remote.dto;

public class LoginRequest {

    /** NIC or email address; the API accepts either. */
    public String username;

    public String password;

    /** Holds the two values the login screen collected. */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
