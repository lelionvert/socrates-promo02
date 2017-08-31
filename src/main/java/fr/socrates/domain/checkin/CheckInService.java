package fr.socrates.domain.checkin;

import fr.socrates.domain.CandidateId;

public interface CheckInService {
    long countCheckinAfter(int hour);
    boolean addNewCheckIn(CheckIn checkIn);
    boolean doesCandidateArriveAfter(CandidateId candidateId, int hour);
}
