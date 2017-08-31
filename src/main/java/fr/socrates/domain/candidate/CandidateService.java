package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.common.AccommodationChoices;

import java.util.List;
import java.util.Optional;
public interface CandidateService {
    void add(Candidate candidate) throws CandidatePersisteDataException, CandidateExistingException;
    List<Candidate> getRegisteredCandidates();
    Optional<Candidate> findCandidateByEmail(String email);
    Optional<Candidate> findCandidateByCandidateID(CandidateId candidateId);
    void update(EMail email, AccommodationChoices accommodationChoices, ContactInformation contactInformation);
}
