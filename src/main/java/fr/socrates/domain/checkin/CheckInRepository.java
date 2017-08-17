package fr.socrates.domain.checkin;

import java.util.List;

public interface CheckInRepository {
    boolean save(CheckIn participantId);
    List<CheckIn> getCheckIns();
}
