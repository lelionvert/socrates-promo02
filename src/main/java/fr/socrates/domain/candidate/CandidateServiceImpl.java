package fr.socrates.domain.candidate;

import fr.socrates.common.Printer;

import java.util.ArrayList;
import java.util.List;

public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final Printer printer;

    public CandidateServiceImpl(CandidateRepository candidateRepository, Printer printer) {
        this.candidateRepository = candidateRepository;
        this.printer = printer;
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
}
