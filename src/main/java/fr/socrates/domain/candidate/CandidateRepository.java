package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;

import java.util.Optional;
import java.util.Set;

public interface CandidateRepository {

    Set<Candidate> findAll();

    boolean save(Candidate candidate);

    Optional<Candidate> findByEmail(String email);

    Optional<Candidate> findByCandidateID(CandidateId candidateId);
}
