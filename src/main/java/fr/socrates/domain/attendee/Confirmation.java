package fr.socrates.domain.attendee;


import fr.socrates.domain.CandidateId;

import java.time.LocalDateTime;

public class Confirmation {
    private final CandidateId candidateId;
    private final LocalDateTime confirmationDate;

    public Confirmation(CandidateId candidateId, LocalDateTime confirmationDate) {

        this.candidateId = candidateId;
        this.confirmationDate = confirmationDate;
    }

    public CandidateId getCandidateId() {
        return candidateId;
    }

    public LocalDateTime getConfirmationDate() {
        return confirmationDate;
    }
}
