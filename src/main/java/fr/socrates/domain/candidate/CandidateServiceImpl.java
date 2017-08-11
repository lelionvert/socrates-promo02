package fr.socrates.domain.candidate;

import fr.socrates.common.Printer;

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
        if (candidateRepository.size() == 0) {
            printer.print("No email to print");
        } else {
            candidateRepository.findAll()
                    .stream()
                    .map(Candidate::toString)
                    .forEach(printer::print);
        }
    }
}
