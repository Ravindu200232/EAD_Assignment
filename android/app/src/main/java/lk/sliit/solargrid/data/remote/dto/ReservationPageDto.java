package lk.sliit.solargrid.data.remote.dto;

import java.util.ArrayList;
import java.util.List;

public class ReservationPageDto {

    public List<ReservationDto> items = new ArrayList<>();
    public long total;
    public int page;
    public int pageSize;
    public int totalPages;

    /** True when there is another page after this one. */
    public boolean hasMore() {
        return page < totalPages;
    }
}
