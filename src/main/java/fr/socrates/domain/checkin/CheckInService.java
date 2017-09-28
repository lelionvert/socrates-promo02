package fr.socrates.domain.checkin;

import fr.socrates.domain.CandidateId;

import java.time.LocalDateTime;

public interface CheckInService {
    long countCheckinAfter(int hour);
    CheckIn addNewCheckIn(CheckIn checkIn);
    boolean isCandidatePresentAt(CandidateId candidateId, LocalDateTime dateTime);
}
