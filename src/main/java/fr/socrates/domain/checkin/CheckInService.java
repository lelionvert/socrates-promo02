package fr.socrates.domain.checkin;

interface CheckInService {
    int getColdFoodCount();
    void addNewCheckInDate(ParticipantId participantId, int hour);
    String print();
}
