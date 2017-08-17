package fr.socrates.domain.candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateService {
    void add(Candidate candidate);

    void print();

    List<Candidate> getRegisteredCandidates();

    Optional<Candidate> findCandidate(Candidate candidate);
}
