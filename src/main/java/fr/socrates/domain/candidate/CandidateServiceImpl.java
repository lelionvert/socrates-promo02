package fr.socrates.domain.candidate;

class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;

    CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public void add(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    @Override
    public int size() {
        return candidateRepository.size();
    }

    @Override
    public boolean hasCandidates() {
        return !candidateRepository.findAll().isEmpty();
    }

    @Override
    public boolean contains(Candidate candidate) {
        return candidateRepository.findAll().contains(candidate);
    }
}
