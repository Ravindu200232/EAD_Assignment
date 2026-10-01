package lk.sliit.solargrid.data.remote.dto;

public class CheckInResponse {

    /** True only inside the check-in window, for an approved booking. */
    public boolean canComplete;

    /** The explanation written by the API, shown on the result screen. */
    public String message;

    public String prosumerPhone;

    /** Start of the window in which the operator may finish the transfer. */
    public String checkInOpensAt;

    /** End of that window. */
    public String checkInClosesAt;

    public ReservationDto reservation;
}
