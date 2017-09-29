package fr.socrates.api.DTO;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.Candidate.CandidateBuilder;
import fr.socrates.domain.candidate.EMail;
import fr.socrates.domain.common.AccommodationChoice;
import fr.socrates.domain.common.AccommodationChoices;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class CandidateDTO {
    @NotNull
    private String email;

    private String[] choices;

    public CandidateDTO() {
    }

    public String getEmail() {
        return email;
    }

    public static CandidateDTO domainToDTO(Candidate candidate){
        final CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.email = candidate.getEmail();
        candidateDTO.choices = Arrays.stream(candidate.getAccommodationChoices().getAccommodationChoices())
                .map(AccommodationChoice::toString)
                .toArray(String[]::new);
        return candidateDTO;
    }

    public static Collection<CandidateDTO> domainToDTO(Collection<Candidate> candidates){
        return candidates.stream()
                .map(CandidateDTO::domainToDTO)
                .collect(Collectors.toList());
    }

    public static Candidate DTOToDomain(CandidateDTO candidateDTO) {
        return CandidateBuilder.aCandidate()
                .withCandidateId(new CandidateId(candidateDTO.getEmail()))
                .withEmail(EMail.of(candidateDTO.getEmail()))
                .withAccommodationChoices(AccommodationChoices.AccommodationChoicesBuilder.anAccommodationChoices()
                        .withAccommodationChoices(Arrays
                                .stream(candidateDTO.choices)
                                .map(AccommodationChoice::valueOf)
                                .toArray(AccommodationChoice[]::new))
                        .build())
                .build();
    }

    public String[] getChoices() {
        return choices;
    }

    public void setAccomodationChoice(AccommodationChoice... choices) {
        this.choices = Arrays.stream(choices)
                .map(AccommodationChoice::toString)
                .toArray(String[]::new);
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
