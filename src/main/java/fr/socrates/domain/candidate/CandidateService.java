package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.candidate.exceptions.CandidateException;

import java.util.List;
import java.util.Optional;

public interface CandidateService {
    boolean add(Candidate candidate) throws CandidateException;
    List<Candidate> getRegisteredCandidates();
    Optional<Candidate> findCandidateByEmail(String email) throws CandidateException;
    Optional<Candidate> findCandidateByCandidateID(CandidateId candidateId) throws CandidateException;
}
