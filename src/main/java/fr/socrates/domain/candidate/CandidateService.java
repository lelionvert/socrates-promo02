package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;

import java.util.List;
import java.util.Optional;
public interface CandidateService {
    boolean add(Candidate candidate);
    List<Candidate> getRegisteredCandidates();
    Optional<Candidate> findCandidateByEmail(String email);
    Optional<Candidate> findCandidateByCandidateID(CandidateId candidateId);
    void update(EMail email, AccommodationChoices accommodationChoices, ContactInformation contactInformation);
}
