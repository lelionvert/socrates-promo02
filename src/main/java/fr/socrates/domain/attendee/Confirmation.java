package fr.socrates.domain.attendee;


import fr.socrates.domain.CandidateId;

import java.time.LocalDate;

public class Confirmation {
    private final CandidateId candidateId;
    private final LocalDate confirmationDate;
    private final Accommodation accommodation;
    private final Payment payment;

    public Confirmation(CandidateId candidateId, LocalDate date, Accommodation accommodation, Payment payment) {
        this.candidateId = candidateId;
        this.confirmationDate = date;
        this.accommodation = accommodation;
        this.payment = payment;
    }

    public CandidateId getCandidateId() {
        return candidateId;
    }

    @Override
    public String toString() {
        return "Confirmation{" +
                "candidateId=" + candidateId +
                ", confirmationDate=" + confirmationDate +
                ", accommodation=" + accommodation +
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
        if (accommodation != that.accommodation) return false;
        return payment == that.payment;
    }

    @Override
    public int hashCode() {
        int result = candidateId.hashCode();
        result = 31 * result + confirmationDate.hashCode();
        result = 31 * result + accommodation.hashCode();
        result = 31 * result + payment.hashCode();
        return result;
    }
}
