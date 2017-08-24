package fr.socrates.domain.attendee;


import fr.socrates.domain.CandidateId;

import java.time.LocalDateTime;

public class Confirmation {
    private final CandidateId candidateId;
    private final LocalDateTime confirmationDate;
    private final Accommodation accommodation;
    private final Payment payment;

    public Confirmation(CandidateId candidateId, LocalDateTime confirmationDate, Accommodation accommodation, Payment payment) {

        this.candidateId = candidateId;
        this.confirmationDate = confirmationDate;
        this.accommodation = accommodation;
        this.payment = payment;
    }

    public CandidateId getCandidateId() {
        return candidateId;
    }

    public LocalDateTime getConfirmationDate() {
        return confirmationDate;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public Payment getPayment() {
        return payment;
    }
}
