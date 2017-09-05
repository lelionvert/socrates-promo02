package fr.socrates.domain.checkin;

import fr.socrates.domain.CandidateId;

import java.util.List;
import java.util.Optional;

public interface CheckInRepository {
    boolean save(CheckIn checkIn);
    List<CheckIn> getCheckIns();
    Optional<CheckIn> getCheckInOf(CandidateId candidateId);
}
