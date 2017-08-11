package fr.socrates.domain.checkin;

import java.time.LocalDateTime;

interface CheckInService {
    int COLD_FOOD_HOUR = 21;

    int getColdFoodCount();
    void addNewCheckInDate(ParticipantId participantId, LocalDateTime checkInDate);
    String print();
}
