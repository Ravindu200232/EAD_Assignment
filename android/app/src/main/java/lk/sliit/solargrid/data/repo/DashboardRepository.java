package lk.sliit.solargrid.data.repo;

import lk.sliit.solargrid.data.remote.ApiCallback;
import lk.sliit.solargrid.data.remote.ApiCalls;
import lk.sliit.solargrid.data.remote.SolarGridApi;
import lk.sliit.solargrid.data.remote.dto.ProsumerDashboardDto;
import lk.sliit.solargrid.data.remote.dto.StaffDashboardDto;

public class DashboardRepository {

    private final SolarGridApi api;

    /** Needs the API calls. */
    public DashboardRepository(SolarGridApi api) {
        this.api = api;
    }

    /** The counts, the next booking and the coming bookings of the signed-in prosumer. */
    public void load(ApiCallback<ProsumerDashboardDto> callback) {
        ApiCalls.enqueue(api.myDashboard(), callback);
    }

    /** The staff numbers over all stations . */
    public void staffSummary(ApiCallback<StaffDashboardDto> callback) {
        ApiCalls.enqueue(api.staffDashboard(), callback);
    }
}
