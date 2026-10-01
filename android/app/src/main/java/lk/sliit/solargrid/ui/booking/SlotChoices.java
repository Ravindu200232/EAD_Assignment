package lk.sliit.solargrid.ui.booking;

import androidx.annotation.Nullable;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import lk.sliit.solargrid.data.remote.dto.ReservationDto;
import lk.sliit.solargrid.data.remote.dto.SlotDto;
import lk.sliit.solargrid.util.Times;

public final class SlotChoices {

    /** Nobody builds this class; it is only a helper. */
    private SlotChoices() {
    }

    /** The slots to offer on one day at one station, earliest first. */
    public static List<SlotDto> forDay(List<SlotDto> freeSlots, @Nullable ReservationDto booking,
                                       String stationId, LocalDate day) {
        List<SlotDto> choices = new ArrayList<>(freeSlots);
        boolean ownDay = booking != null
                && stationId.equals(booking.stationId)
                && day.equals(Times.dayOf(booking.startTime));
        if (ownDay && !contains(choices, booking.slotId)) {
            SlotDto own = new SlotDto();
            own.id = booking.slotId;
            own.stationId = booking.stationId;
            own.startTime = booking.startTime;
            own.endTime = booking.endTime;
            own.isOpen = true;
            choices.add(own);
        }
        choices.sort(Comparator.comparing(slot -> startOf(slot)));
        return choices;
    }

    /** True for the slot the booking being changed already holds. */
    public static boolean isOwn(SlotDto slot, @Nullable ReservationDto booking) {
        return booking != null && slot.id != null && slot.id.equals(booking.slotId);
    }

    /** True when a slot with this id is in the list. */
    private static boolean contains(List<SlotDto> slots, @Nullable String slotId) {
        for (SlotDto slot : slots) {
            if (slot.id != null && slot.id.equals(slotId)) {
                return true;
            }
        }
        return false;
    }

    /** The start of a slot; one that cannot be read goes last. */
    private static Instant startOf(SlotDto slot) {
        Instant start = Times.toInstant(slot.startTime);
        return start == null ? Instant.MAX : start;
    }
}
