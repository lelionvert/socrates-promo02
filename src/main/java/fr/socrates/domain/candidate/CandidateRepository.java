package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository {
    List<Candidate> findAll();

    boolean save(Candidate candidate);

    Optional<Candidate> findByEmail(String email);

    Optional<Candidate> findByCandidateID(CandidateId candidateId);

    void updateContactInfos(CandidateId candidateId, ContactInformation contactInformation);

    void updateAccommodationChoices(CandidateId candidateId, AccommodationChoices accommodationChoices);
}
