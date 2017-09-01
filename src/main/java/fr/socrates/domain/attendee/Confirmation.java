package fr.socrates.domain.attendee;


import fr.socrates.domain.CandidateId;
import fr.socrates.domain.common.AccommodationChoice;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Confirmation {
    private final CandidateId candidateId;
    private final LocalDate confirmationDate;
    private AccommodationChoice accommodationChoice;
    private Payment payment;

    public Confirmation(CandidateId candidateId, LocalDate confirmationDate) {
        this.candidateId = candidateId;
        this.confirmationDate = confirmationDate;
    }

    public Confirmation(CandidateId candidateId, LocalDate date, AccommodationChoice accommodationChoice, Payment payment) {
        this(candidateId, date);
        this.accommodationChoice = accommodationChoice;
        this.payment = payment;
    }

    public CandidateId getCandidateId() {
        return candidateId;
    }

    public LocalDate getConfirmationDate() {
        return confirmationDate;
    }

    public Payment getPayment() {
        return payment;
    }

    public AccommodationChoice getAccommodationChoice() {
        return accommodationChoice;
    }

    @Override
    public String toString() {
        return "Confirmation{" +
                "candidateId=" + candidateId +
                ", confirmationDate=" + confirmationDate +
                ", accommodationChoice=" + accommodationChoice +
                ", payment=" + payment +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Confirmation that = (Confirmation) o;

        if (!candidateId.equals(that.candidateId)) return false;
        if (!confirmationDate.equals(that.confirmationDate)) return false;
        if (accommodationChoice != that.accommodationChoice) return false;
        return payment == that.payment;
    }

    @Override
    public int hashCode() {
        int result = candidateId.hashCode();
        result = 31 * result + confirmationDate.hashCode();
        result = 31 * result + accommodationChoice.hashCode();
        result = 31 * result + payment.hashCode();
        return result;
    }
}
