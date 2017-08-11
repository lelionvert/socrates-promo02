package fr.socrates.domain.checkin;

import java.util.List;

interface CheckInRepository {
    void save(CheckIn participantId);
    List<CheckIn> getCheckIns();
}
