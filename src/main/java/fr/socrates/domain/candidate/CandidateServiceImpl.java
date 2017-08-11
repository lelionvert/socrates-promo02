package fr.socrates.domain.candidate;

import fr.socrates.common.Printer;

class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final Printer printer;

    CandidateServiceImpl(CandidateRepository candidateRepository, Printer printer) {
        this.candidateRepository = candidateRepository;
        this.printer = printer;
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

    @Override
    public void print() {
        String emailToPrint = candidateRepository.findAll()
                .stream()
                .map(Candidate::toString)
                .findFirst()
                .orElse("No email to print");
        printer.print(emailToPrint);
    }
}
