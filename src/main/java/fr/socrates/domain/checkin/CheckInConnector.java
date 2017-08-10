package fr.socrates.domain.checkin;

import java.util.List;

interface CheckInConnector {
    void save(CheckIn participantId);
    List<CheckIn> getCheckIns();
}
