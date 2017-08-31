package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.common.AccommodationChoices;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public void add(Candidate candidate) throws CandidatePersisteDataException, CandidateExistingException {
        checkThatCandidateDoesntExist(candidate);
        saveCandidate(candidate);
    }

    private void checkThatCandidateDoesntExist(Candidate candidate) throws CandidateExistingException {
        if (candidateRepository.findByEmail(candidate.getEmail().getEmail()).isPresent()) {
            throw new CandidateExistingException("Candidate Already exist");
        }
    }

    private void saveCandidate(Candidate candidate) throws CandidatePersisteDataException {
        final boolean successfulSave = candidateRepository.save(candidate);
        if (!successfulSave) {
            throw new CandidatePersisteDataException("Cannot Save Candidate ");
        }
    }

    @Override
    public List<Candidate> getRegisteredCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        candidates.addAll(candidateRepository.findAll());
        return candidates;
    }

    @Override
    public Optional<Candidate> findCandidateByEmail(String email) {
        return candidateRepository.findByEmail(email);
    }

    @Override
    public Optional<Candidate> findCandidateByCandidateID(CandidateId candidateId) {
        return candidateRepository.findByCandidateID(candidateId);
    }


    @Override
    public void update(EMail email, AccommodationChoices accommodationChoices, ContactInformation contactInformation) {
        final Optional<Candidate> candidate = candidateRepository.findByEmail(email.getEmail());
        if (!candidate.isPresent()) {
            throw new RuntimeException("the Candidate doesn't exist");
        }
        final CandidateId candidateId = candidate.get().getCandidateId();
        if (accommodationChoices != null) {
            candidateRepository.updateAccommodationChoices(candidateId, accommodationChoices);
        }
        if (contactInformation != null) {
            candidateRepository.updateContactInfos(candidateId, contactInformation);
        }
    }
}
