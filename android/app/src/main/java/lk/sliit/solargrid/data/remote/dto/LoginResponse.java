package lk.sliit.solargrid.data.remote.dto;

public class LoginResponse {

    public String token;

    /** UTC time when the token stops working (8 hours after login). */
    public String expiresAt;

    public UserDto user;
}
