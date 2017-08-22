package fr.socrates.infra.repositories;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateRepository;

import java.util.*;

public class InMemoryCandidateRepository implements CandidateRepository {
    private final List<Candidate> candidateList = new ArrayList<>();

    @Override
    public List<Candidate> findAll() {
        return candidateList;
    }

    @Override
    public boolean save(Candidate candidate) {
        return candidateList.add(candidate);
    }

    @Override
    public Optional<Candidate> findByEmail(String email) {
        return candidateList.stream().filter(candidate -> candidate.hasEmail(email)).findFirst();
    }

    @Override
    public Optional<Candidate> findByCandidateID(CandidateId candidateId) {
        return candidateList.stream().filter(candidate -> candidate.hasCandidateID(candidateId)).findFirst();
    }
}
