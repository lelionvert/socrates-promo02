package fr.socrates.api.DTO;

import fr.socrates.domain.candidate.Candidate;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.stream.Collectors;

public class CandidateDTO {
    @NotNull
    private String email;

    public CandidateDTO() {
    }

    public CandidateDTO(String emaildto) {
        this.email = emaildto;
    }

    public String getEmail() {
        return email;
    }

    public static CandidateDTO domainToDTO(Candidate candidate){
        return new CandidateDTO(candidate.getEmail().getEmail());
    }

    public static Collection<CandidateDTO> domainToDTO(Collection<Candidate> candidates){
        return candidates.stream()
                .map(CandidateDTO::domainToDTO)
                .collect(Collectors.toList());
    }

    public static Candidate DTOToDomain(CandidateDTO candidateDTO) {
        return Candidate.withEmail(candidateDTO.getEmail());
    }
}
