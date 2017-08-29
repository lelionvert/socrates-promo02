package fr.socrates.domain.candidate;

import java.util.List;
import java.util.Optional;
public interface CandidateService {
    boolean add(Candidate candidate);
    List<Candidate> getRegisteredCandidates();
    Optional<Candidate> findCandidateByEmail(String email);

    Candidate update(Candidate candidateToUpdate);
    void update(EMail email, AccommodationChoices accommodationChoices, ContactInformation contactInformation);
}
