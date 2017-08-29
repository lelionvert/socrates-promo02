package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;
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
    public boolean add(Candidate candidate) {
        if (candidateRepository.findByCandidateID(candidate.getCandidateId()).isPresent())
            return false;
        return candidateRepository.save(candidate);
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
    public void update(EMail email, AccommodationChoices accommodationChoices, ContactInformation contactInformation) {
        final Optional<Candidate> candidate = candidateRepository.findByEmail(email.getEmail());
        if (candidate.isPresent()) {
            final CandidateId candidateId = candidate.get().getCandidateId();
            if (null != accommodationChoices)
                candidateRepository.updateAccommodationChoices(candidateId, accommodationChoices);
            if (null != contactInformation)
                candidateRepository.updateContactInfos(candidateId, contactInformation);
        } else {
            throw new RuntimeException("the Candidate doesn't exist");
        }
    }
}
