package fr.socrates.domain.candidate;

import java.util.Optional;
import java.util.Set;

public interface CandidateRepository {

    Set<Candidate> findAll();

    void save(Candidate candidate);

    Optional<Candidate> findByEmail(String email);
}
