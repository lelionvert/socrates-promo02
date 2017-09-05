package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.common.AccommodationChoices;
import fr.socrates.domain.meal.Diet;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository {
    List<Candidate> findAll();

    boolean save(Candidate candidate);

    Optional<Candidate> findByEmail(String email);

    Optional<Candidate> findByCandidateID(CandidateId candidateId);

    void updateDietOf(CandidateId candidateId, Diet diet);

    void updateContactInfos(CandidateId candidateId, ContactInformation contactInformation);

    void updateAccommodationChoices(CandidateId candidateId, AccommodationChoices accommodationChoices);
}
