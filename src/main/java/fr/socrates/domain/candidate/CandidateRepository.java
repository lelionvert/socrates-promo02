package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.candidate.exceptions.AddCandidateException;
import fr.socrates.domain.candidate.exceptions.CandidateException;
import fr.socrates.domain.candidate.exceptions.UnknownCandidateException;

import java.util.List;

public interface CandidateRepository {
    List<Candidate> findAll();

    Candidate addCandidate(Candidate candidate) throws AddCandidateException;

    Candidate updateCandidate(Candidate updateOfCandidate, String oldEmail) throws CandidateException;

    Candidate findCandidateByEmail(String email) throws CandidateException;

    Candidate findCandidateById(CandidateId candidateId) throws UnknownCandidateException;
}
