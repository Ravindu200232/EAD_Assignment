using SolarGrid.Api.Dtos;
using SolarGrid.Api.Security;

namespace SolarGrid.Api.Services;

public interface IDashboardService
{
    Task<StaffDashboardResponse> GetStaffSummaryAsync();

    Task<ProsumerDashboardResponse> GetProsumerSummaryAsync(CurrentUser caller);

    Task<PublicSummaryResponse> GetPublicSummaryAsync();
}
