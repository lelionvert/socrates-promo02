package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.candidate.exceptions.CandidateException;
import fr.socrates.domain.common.AccommodationChoices;

import java.util.List;
import java.util.Optional;

public interface CandidateService {
    void add(Candidate candidate) throws CandidateException;

    List<Candidate> getRegisteredCandidates();

    Optional<Candidate> findCandidateByEmail(String email) throws CandidateException;

    Optional<Candidate> findCandidateByCandidateID(CandidateId candidateId) throws CandidateException;

    void update(EMail email, AccommodationChoices accommodationChoices, ContactInformation contactInformation) throws CandidateException;
}
