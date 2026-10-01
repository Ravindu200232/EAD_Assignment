package lk.sliit.solargrid.data.remote.dto;

import java.util.ArrayList;
import java.util.List;

public class StaffDashboardDto {

    /** Bookings waiting for Backoffice approval. */
    public long pendingReservations;

    /** Approved bookings whose time has not come yet. */
    public long approvedFutureReservations;

    /** Bookings that start today (Sri Lankan time), whatever their status. */
    public long todaysReservations;

    public long activeStations;
    public long totalStations;
    public long pendingActivations;
    public long activeProsumers;

    /** The next bookings at all stations, soonest first. */
    public List<BookingSummaryDto> upcomingReservations = new ArrayList<>();

    /** When the API worked the numbers out (UTC). */
    public String generatedAt;
}
