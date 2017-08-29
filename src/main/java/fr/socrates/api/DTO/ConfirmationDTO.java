package fr.socrates.api.DTO;

import fr.socrates.domain.attendee.Accommodation;
import fr.socrates.domain.attendee.Confirmation;
import fr.socrates.domain.attendee.Payment;
import fr.socrates.domain.candidate.CandidateService;
import fr.socrates.domain.candidate.CandidateServiceImpl;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.stream.Collectors;

public class ConfirmationDTO {
    @NotNull
    private String email;
    @NotNull
    private Accommodation accommodation;
    @NotNull
    private Payment payment;


    public ConfirmationDTO() {
    }

    public ConfirmationDTO(String email, Accommodation accommodation, Payment payment) {
        this.email = email;
        this.accommodation = accommodation;
        this.payment = payment;
    }

    public String getEmail() {
        return email;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static ConfirmationDTO mapToDTO(String candidateEmail, Accommodation accommodation, Payment payment) {
        // TODO #Demeter
        return new ConfirmationDTO(candidateEmail, accommodation, payment);
    }
}
