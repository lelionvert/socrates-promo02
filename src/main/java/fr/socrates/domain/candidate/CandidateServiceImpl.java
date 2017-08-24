package fr.socrates.domain.candidate;

import org.springframework.stereotype.Service;

import fr.socrates.domain.CandidateId;

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
    public Optional<Candidate> findCandidateByCandidateID(CandidateId candidateId) {
        return candidateRepository.findByCandidateID(candidateId);
    }

}
