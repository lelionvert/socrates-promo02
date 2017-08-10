package fr.socrates.domain.checkin;

/**
 * Created by lenovo_14 on 10/08/2017.
 */
public interface CheckingStorage {
    void add(ParticipantID participantID);
    int getCheckings();
}
