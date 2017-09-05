package fr.socrates.infra.repositories;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateRepository;
import fr.socrates.domain.candidate.ContactInformation;
import fr.socrates.domain.candidate.EMail;
import fr.socrates.domain.common.AccommodationChoices;
import fr.socrates.domain.meal.Diet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryCandidateRepository implements CandidateRepository {
    private final List<Candidate> candidateList = new ArrayList<>();


    @Override
    public List<Candidate> findAll() {
        return candidateList;
    }

    @Override
    public boolean save(Candidate candidate) {
        return candidateList.add(candidate);
    }

    @Override
    public Optional<Candidate> findByEmail(String email) {
        return candidateList.stream().filter(candidate -> candidate.hasEmail(email)).findFirst();
    }

    @Override
    public Optional<Candidate> findByCandidateID(CandidateId candidateId) {
        return candidateList.stream().filter(candidate -> candidate.hasCandidateID(candidateId)).findFirst();
    }

    @Override
    public void updateContactInfos(CandidateId candidateId, ContactInformation contactInformation) {
        final Optional<Candidate> candidate = findByCandidateID(candidateId);
        if (candidate.isPresent()) {
            final Candidate candidateToUpdate = candidate.get();

            final Candidate candidateUpdated = Candidate.CandidateBuilder.aCandidate()
                    .withCandidateId(candidateToUpdate.getCandidateId())
                    .withAccommodationChoices(candidateToUpdate.getAccommodationChoices())
                    .withEmail(EMail.of(candidateToUpdate.getEmail()))
                    .withContactInformations(contactInformation)
                    .build();
            candidateList.remove(candidateToUpdate);
            candidateList.add(candidateUpdated);
        }
    }

    @Override
    public void updateAccommodationChoices(CandidateId candidateId, AccommodationChoices accommodationChoices) {
        final Optional<Candidate> candidate = findByCandidateID(candidateId);
        if (candidate.isPresent()) {
            final Candidate candidateToUpdate = candidate.get();

            final Candidate candidateUpdated = Candidate.CandidateBuilder.aCandidate()
                    .withCandidateId(candidateToUpdate.getCandidateId())
                    .withAccommodationChoices(accommodationChoices)
                    .withEmail(EMail.of(candidateToUpdate.getEmail()))
                    .withContactInformations(candidateToUpdate.getContactInformation())
                    .build();
            candidateList.remove(candidateToUpdate);
            candidateList.add(candidateUpdated);
        }
    }

    @Override
    public void updateDietOf(CandidateId candidateId, Diet diet) {
        Optional<Candidate> foundCandidate = candidateList.stream().filter(candidate -> candidate.hasCandidateID(candidateId)).findAny();
        if (foundCandidate.isPresent()) {
            candidateList.remove(foundCandidate.get());
            candidateList.add(Candidate.CandidateBuilder.fromCandidate(foundCandidate.get()).withDiet(diet).build());
        }
    }
}
