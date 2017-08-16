package fr.socrates.domain.candidate;

import java.util.Collection;

public interface CandidateService {
    void add(Candidate candidate);

    @Deprecated
    void print();

    Collection<Candidate> getRegisteredCandidates();
}
