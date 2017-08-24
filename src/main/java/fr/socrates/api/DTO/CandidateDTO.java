package fr.socrates.api.DTO;

import fr.socrates.domain.candidate.Candidate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CandidateDTO {
    private EmailDTO email;

    public CandidateDTO() {
    }

    public CandidateDTO(EmailDTO emaildto) {
        this.email = emaildto;
    }

    public EmailDTO getEmail() {
        return email;
    }

    public static CandidateDTO domainToDTO(Candidate candidate){
        return new CandidateDTO(EmailDTO.domainToDTO(candidate.getEmail()));
    }

    public static Collection<CandidateDTO> domainToDTO(Collection<Candidate> candidates){
        List<CandidateDTO> candidatesDtoList = new ArrayList<>();
        candidates.forEach(candidate -> candidatesDtoList.add(domainToDTO(candidate)));
        return candidatesDtoList;
    }

    public static Candidate DTOToDomain(CandidateDTO candidateDTO) {
        // TODO #Demeter
        return Candidate.withEmail(candidateDTO.getEmail().getEmail());
    }
}
