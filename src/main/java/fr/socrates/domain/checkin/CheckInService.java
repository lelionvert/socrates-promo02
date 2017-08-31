package fr.socrates.domain.checkin;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.candidate.Candidate;

public interface CheckInService {
    long countCheckinAfter(int hour);
    boolean addNewCheckIn(CheckIn checkIn);
    long getTimeOfArrivalOf(CandidateId candidateId);
}
