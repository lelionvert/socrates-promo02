package fr.socrates.domain.checkin;

interface CheckInService {

    int getColdFoodCount();
    void addNewCheckinDate(ParticipantId participantId, int hour);
    String print();
}
