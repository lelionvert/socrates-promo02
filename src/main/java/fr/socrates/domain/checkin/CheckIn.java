package fr.socrates.domain.checkin;

import java.time.LocalDateTime;

class CheckIn {
    private final ParticipantId participantId;
    private final LocalDateTime checkInDate;
    int COLD_FOOD_HOUR = 21;

    public CheckIn(ParticipantId participantId, LocalDateTime checkInDate) {
        this.participantId = participantId;
        this.checkInDate = checkInDate;
    }

    public ParticipantId getParticipantId() {
        return participantId;
    }


    public boolean isCheckInDateForColdMeal() {
        return checkInDate.getHour() >= COLD_FOOD_HOUR;
    }
}
