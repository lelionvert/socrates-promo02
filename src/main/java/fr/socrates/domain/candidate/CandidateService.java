package fr.socrates.domain.candidate;

public interface CandidateService {
    void add(Candidate candidate);

    int size();

    boolean hasCandidates();

    boolean contains(Candidate candidate);

    String print();
}
