package fr.socrates.domain.checkin;

import fr.socrates.domain.CandidateId;

import java.time.LocalDateTime;

public class CheckIn {
    private final CandidateId candidateId;
    private final LocalDateTime checkInDate;

    public CheckIn(CandidateId candidateId, LocalDateTime checkInDate) {
        this.candidateId = candidateId;
        this.checkInDate = checkInDate;
    }

    public CandidateId getCandidateId() {
        return candidateId;
    }

    public int getCheckInHour() {
        return checkInDate.getHour();
    }
    public boolean wasBefore(LocalDateTime dateTime){
        return checkInDate.isBefore(dateTime);
    }
}
