package fr.socrates.infra.storage.repositories;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.exceptions.CandidateException;
import fr.socrates.domain.candidate.CandidateRepository;
import fr.socrates.domain.candidate.exceptions.UnknownCandidateException;

import java.util.*;

public class InMemoryCandidateRepository implements CandidateRepository {
    private final List<Candidate> candidateList = new ArrayList<>();

    @Override
    public List<Candidate> findAll() {
        return candidateList;
    }

    @Override
    public Candidate addCandidate(Candidate candidate) {
        candidateList.add(candidate);
        return candidate;
    }

    @Override
    public Candidate updateCandidate(Candidate updateOfCandidate, String oldEmail) {
        return updateOfCandidate;
    }

    @Override
    public Candidate findCandidateByEmail(String email) throws CandidateException {
        Optional<Candidate> foundCandidate = candidateList.stream().filter(candidate -> candidate.hasEmail(email)).findFirst();
        if (foundCandidate.isPresent())
            return foundCandidate.get();
        throw new UnknownCandidateException();
    }

    @Override
    public Candidate findCandidateById(CandidateId candidateId) {
        Optional<Candidate> foundCandidate = candidateList.stream().filter(candidate -> candidate.hasCandidateID(candidateId)).findFirst();
        if (foundCandidate.isPresent())
            return foundCandidate.get();
        return null;
    }
}
