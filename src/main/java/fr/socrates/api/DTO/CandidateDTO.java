package fr.socrates.api.DTO;

import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.Candidate.CandidateBuilder;
import fr.socrates.domain.candidate.EMail;
import fr.socrates.domain.common.AccommodationChoice;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.stream.Collectors;

public class CandidateDTO {
    @NotNull
    private String email;

    private String choice;

    public CandidateDTO() {
    }

    public String getEmail() {
        return email;
    }

    public static CandidateDTO domainToDTO(Candidate candidate){
        final CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.email = candidate.getEmail();
        candidateDTO.choice = candidate.getAccommodationChoices().getFirstChoice().getAccommodationChoiceValue();
        return candidateDTO;
    }

    public static Collection<CandidateDTO> domainToDTO(Collection<Candidate> candidates){
        return candidates.stream()
                .map(CandidateDTO::domainToDTO)
                .collect(Collectors.toList());
    }

    public static Candidate DTOToDomain(CandidateDTO candidateDTO) {
        return CandidateBuilder.aCandidate()
                .withEmail(EMail.of(candidateDTO.getEmail()))
                .withOneAccommodationChoice(AccommodationChoice.SINGLE_ROOM)
                .build();
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
