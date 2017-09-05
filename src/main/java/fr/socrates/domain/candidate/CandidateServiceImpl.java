package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;

import fr.socrates.domain.candidate.exceptions.AddCandidateException;
import fr.socrates.domain.candidate.exceptions.CandidateException;
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
    public void add(Candidate candidate) throws CandidateException {
        if (!doesCandidateExists(candidate))
            saveCandidate(candidate);
    }

    private boolean doesCandidateExists(Candidate candidate) {
        try {
            candidateRepository.findCandidateByEmail(candidate.getEmail());
            return true;
        } catch (CandidateException e) {
            return false;
        }
    }

    private void saveCandidate(Candidate candidate) throws fr.socrates.domain.candidate.exceptions.CandidateException.CandidatePersisteDataException, AddCandidateException {
        final Candidate savedCandidate = candidateRepository.addCandidate(candidate);
        if (savedCandidate == null) {
            throw new CandidateException.CandidatePersisteDataException();
        }
    }

    @Override
    public List<Candidate> getRegisteredCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        candidates.addAll(candidateRepository.findAll());
        return candidates;
    }

    @Override
    public Optional<Candidate> findCandidateByEmail(String email) throws CandidateException {
        return Optional.of(candidateRepository.findCandidateByEmail(email));
    }

    @Override
    public Optional<Candidate> findCandidateByCandidateID(CandidateId candidateId) throws CandidateException {
        return Optional.of(candidateRepository.findCandidateById(candidateId));
    }


    @Override
    public void update(EMail email, AccommodationChoices accommodationChoices, ContactInformation contactInformation) throws CandidateException {
        final Optional<Candidate> candidate = Optional.of(candidateRepository.findCandidateByEmail(email.getEmail()));
        if (!candidate.isPresent()) {
            throw new fr.socrates.domain.candidate.exceptions.CandidateException.NotFoundException();
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
