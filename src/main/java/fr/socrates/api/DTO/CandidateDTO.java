package fr.socrates.api.DTO;

import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.Candidate.CandidateBuilder;
import fr.socrates.domain.candidate.EMail;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.stream.Collectors;

public class CandidateDTO {
    @NotNull
    private String email;

    public CandidateDTO() {
    }

    private CandidateDTO(String emaildto) {
        this.email = emaildto;
    }

    public String getEmail() {
        return email;
    }

    public static CandidateDTO domainToDTO(Candidate candidate){
        return new CandidateDTO(candidate.getEmail());
    }

    public static Collection<CandidateDTO> domainToDTO(Collection<Candidate> candidates){
        return candidates.stream()
                .map(CandidateDTO::domainToDTO)
                .collect(Collectors.toList());
    }

    public static Candidate DTOToDomain(CandidateDTO candidateDTO) {
        return CandidateBuilder.aCandidate()
                .withEmail(EMail.of(candidateDTO.getEmail()))
                .build();
    }
}
