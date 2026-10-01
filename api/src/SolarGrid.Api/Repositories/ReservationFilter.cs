using SolarGrid.Api.Models;

namespace SolarGrid.Api.Repositories;

public class ReservationFilter
{
    public string? ProsumerNic { get; set; }

    public string? StationId { get; set; }

    public IReadOnlyCollection<ReservationStatus>? Statuses { get; set; }

    // Only bookings that have not ended yet.
    public DateTime? EndsAfterUtc { get; set; }

    // Only finished bookings: completed, cancelled, rejected, or already ended at this time.
    public DateTime? HistoryAtUtc { get; set; }

    public DateTime? StartFromUtc { get; set; }

    public DateTime? StartBeforeUtc { get; set; }

    // Matches reference number, station name, prosumer name or NIC.
    public string? Search { get; set; }

    public bool NewestFirst { get; set; } = true;
}
