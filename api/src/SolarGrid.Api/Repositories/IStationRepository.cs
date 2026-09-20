using SolarGrid.Api.Models;

namespace SolarGrid.Api.Repositories;

public interface IStationRepository
{
    Task<SolarStation?> GetByIdAsync(string id);

    Task<SolarStation?> GetByCodeAsync(string code);

    Task<IReadOnlyList<SolarStation>> ListAsync(StationStatus? status, string? search);

    Task InsertAsync(SolarStation station);

    Task InsertManyAsync(IEnumerable<SolarStation> stations);

    Task UpdateAsync(SolarStation station);

    // Helper for dashboards.
    Task<long> CountAsync(StationStatus? status = null);

    // Helper for the map.
    Task<IReadOnlyList<(SolarStation Station, double DistanceKm)>> FindNearbyAsync(
        double latitude, double longitude, double radiusKm, int limit);
}
