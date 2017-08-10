package fr.socrates.domain.candidate;

class CandidateService {
    private final CandidateRepository candidateRepository;

    CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    void add(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    public int size() {
        return candidateRepository.size();
    }

    public boolean hasCandidates() {
        return !candidateRepository.findAll().isEmpty();
    }

    public boolean contains(Candidate candidate) {
        return candidateRepository.findAll().contains(candidate);
    }
}
