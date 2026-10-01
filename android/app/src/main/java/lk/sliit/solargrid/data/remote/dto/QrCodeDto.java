package lk.sliit.solargrid.data.remote.dto;

public class QrCodeDto {

    public String reservationId;
    public String referenceNo;
    public String stationName;
    public String startTime;
    public String endTime;

    /** The signed text, for example "SSG1.id.nonce.signature". */
    public String payload;
}
