package fr.socrates.domain.checkin;

import java.time.LocalDateTime;

public class CheckIn {
    private final AttendeeId attendeeId;
    private final LocalDateTime checkInDate;

    public CheckIn(AttendeeId attendeeId, LocalDateTime checkInDate) {
        this.attendeeId = attendeeId;
        this.checkInDate = checkInDate;
    }

    public AttendeeId getAttendeeId() {
        return attendeeId;
    }

    public int getCheckInHour() {
        return checkInDate.getHour();
    }
}
