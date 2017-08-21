package fr.socrates.infra.repositories;

import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateRepository;

import java.util.LinkedHashSet;
import java.util.Set;

public class InMemoryCandidateRepository implements CandidateRepository {

    private final Set<Candidate> list = new LinkedHashSet<>();

    @Override
    public Set<Candidate> findAll() {
        return list;
    }

    @Override
    public void save(Candidate candidate) {
        list.add(candidate);
    }

}
