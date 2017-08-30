package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.candidate.exceptions.CandidateException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public boolean add(Candidate candidate) throws CandidateException {
        return candidateRepository.findCandidateById(candidate.getCandidateId()) == null && candidateRepository.addCandidate(candidate) != null;
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
}
