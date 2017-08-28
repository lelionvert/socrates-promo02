package fr.socrates.api.DTO;

import fr.socrates.domain.candidate.Candidate;

import java.util.Collection;
import java.util.stream.Collectors;

public class ConfirmationDTO {
    private String email;

    public ConfirmationDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static ConfirmationDTO domainToDTO(Candidate candidate) {
        // TODO #Demeter
        return new ConfirmationDTO(candidate.getEmail().getEmail());
    }

    public static Collection<ConfirmationDTO> domainToDTO(Collection<Candidate> candidates) {
        return candidates.stream()
                .map(ConfirmationDTO::domainToDTO)
                .collect(Collectors.toList());
    }
}
