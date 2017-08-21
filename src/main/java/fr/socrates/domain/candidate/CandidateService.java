package fr.socrates.domain.candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateService {
    void add(Candidate candidate);
    List<Candidate> getRegisteredCandidates();
    Optional<Candidate> findCandidateByEmail(String email);
}
