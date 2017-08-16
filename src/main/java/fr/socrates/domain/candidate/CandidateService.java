package fr.socrates.domain.candidate;

import java.util.List;

public interface CandidateService {
    void add(Candidate candidate);

    List<Candidate> getRegisteredCandidates();
}
