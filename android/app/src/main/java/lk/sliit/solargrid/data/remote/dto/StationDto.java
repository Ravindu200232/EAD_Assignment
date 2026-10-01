package lk.sliit.solargrid.data.remote.dto;

import java.util.ArrayList;
import java.util.List;

public class StationDto {

    public String id;

    /** Short code such as "MLB-01". */
    public String code;

    public String name;
    public String address;
    public double latitude;
    public double longitude;

    public double solarCapacityKw;
    public double storageCapacityKwh;

    public int totalBatterySlots;
    public int availableBatterySlots;

    /** The most energy one booking may use at this station. */
    public double bayCapacityKwh;

    /** Opening hours for each day of the week that the station opens. */
    public List<OpeningHoursDto> schedule = new ArrayList<>();

    /** "Active" or "Inactive". */
    public String status;

    /** Only filled by the nearby search: kilometres from the prosumer. */
    public Double distanceKm;

    /** One day of the weekly schedule, for example Monday 06:00 to 18:00. */
    public static class OpeningHoursDto {

        /** "Monday" to "Sunday". */
        public String day;

        /** "06:00" - Sri Lankan time. */
        public String openTime;

        /** "18:00" - Sri Lankan time; "24:00" means open until midnight. */
        public String closeTime;
    }
}
