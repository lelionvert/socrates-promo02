package fr.socrates.domain.checkin;

import java.util.List;

/**
 * Created by lenovo_14 on 10/08/2017.
 */
public interface CheckingStorage {
    void save(ParticipantId participantId);
    List<Checking> getCheckings();
}
