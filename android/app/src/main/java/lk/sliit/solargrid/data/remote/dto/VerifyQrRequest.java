package lk.sliit.solargrid.data.remote.dto;

public class VerifyQrRequest {

    public String payload;

    /** Carries the scanned (or typed) code. */
    public VerifyQrRequest(String payload) {
        this.payload = payload;
    }
}
