package lk.sliit.solargrid.data.remote.dto;

public class CompleteTransferRequest {

    public String payload;

    public Double deliveredKwh;

    /** Carries the code and the measured kWh. */
    public CompleteTransferRequest(String payload, Double deliveredKwh) {
        this.payload = payload;
        this.deliveredKwh = deliveredKwh;
    }
}
