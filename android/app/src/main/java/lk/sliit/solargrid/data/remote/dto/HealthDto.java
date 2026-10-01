package lk.sliit.solargrid.data.remote.dto;

public class HealthDto {

    /** "Healthy" or "Unhealthy". */
    public String status;

    /** "Connected" or "Not reachable". */
    public String database;

    public String serverTimeUtc;
}
