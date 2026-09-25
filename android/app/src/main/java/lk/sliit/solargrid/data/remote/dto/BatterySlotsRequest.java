package lk.sliit.solargrid.data.remote.dto;

public class BatterySlotsRequest {

    public int availableBatterySlots;

    /** Holds the new number of free bays. */
    public BatterySlotsRequest(int availableBatterySlots) {
        this.availableBatterySlots = availableBatterySlots;
    }
}
