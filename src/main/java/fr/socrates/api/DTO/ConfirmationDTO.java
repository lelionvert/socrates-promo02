package fr.socrates.api.DTO;

import fr.socrates.domain.attendee.Payment;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.common.AccommodationChoice;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.stream.Collectors;

public class ConfirmationDTO {
    @NotNull
    private String email;
    @NotNull
    private String accommodationChoice;
    @NotNull
    private String payment;


    public ConfirmationDTO() {
    }

    public ConfirmationDTO(String email) {
        this.email = email;
        this.accommodationChoice = AccommodationChoice.SINGLE_ROOM.toString();
        this.payment = Payment.TRANSFER.toString();
    }

    public String getEmail() {
        return email;
    }

    public String getAccommodationChoice() {
        return accommodationChoice;
    }

    public String getPayment() {
        return payment;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static ConfirmationDTO domainToDTO(Candidate candidate) {
        return new ConfirmationDTO(candidate.getEmail());
    }

    public static Collection<ConfirmationDTO> domainToDTO(Collection<Candidate> candidates) {
        return candidates.stream()
                .map(ConfirmationDTO::domainToDTO)
                .collect(Collectors.toList());
    }
}
