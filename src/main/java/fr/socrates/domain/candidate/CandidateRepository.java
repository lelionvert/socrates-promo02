package fr.socrates.domain.candidate;

import java.util.Set;

public interface CandidateRepository {

    Set<Candidate> findAll();

    void save(Candidate candidate);

    int size();
}
