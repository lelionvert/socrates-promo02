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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Confirmation that = (Confirmation) o;

        return candidateId.equals(that.candidateId);
    }

    @Override
    public int hashCode() {
        return candidateId.hashCode();
    }

    public CandidateId getCandidateId() {
        return candidateId;
    }

    public LocalDateTime getConfirmationDate() {
        return confirmationDate;
    }
}
