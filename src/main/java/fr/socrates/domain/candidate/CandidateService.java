package fr.socrates.domain.candidate;

import java.util.Set;

class CandidateService {
    private final CandidateRepository candidateRepository;

    CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    Set<Candidate> getCandidates() {
        return candidateRepository.findAll();
    }

    void add(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    public int size() {
        return candidateRepository.size();
    }
}
