package fr.socrates.domain.candidate;

import fr.socrates.common.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
