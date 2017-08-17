package fr.socrates.domain.candidate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public void add(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    @Override
    public List<Candidate> getRegisteredCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        candidates.addAll(candidateRepository.findAll());
        return candidates;
    }

    @Override
    public Optional<Candidate> findCandidate(Candidate candidate) {
       return candidateRepository.findAll().stream().filter(e -> e.equals(candidate)).findAny();
    }
}
