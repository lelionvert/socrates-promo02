package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.meal.Diet;

public class CandidateBuilder {
    private CandidateId candidateId;
    private EMail email;
    private Diet diet = Diet.NORMAL;

    public CandidateBuilder withCandidateId(CandidateId candidateId) {
        this.candidateId = candidateId;
        return this;
    }

    public CandidateBuilder withEmail(EMail email) {
        this.email = email;
        return this;
    }

    public CandidateBuilder withEmail(String email) {
        this.email = EMail.of(email);
        return this;
    }

    public CandidateBuilder withDiet(Diet diet) {
        this.diet = diet;
        return this;
    }

    public Candidate createCandidate() {
        return new Candidate(candidateId, email, diet);
    }

    public CandidateBuilder fromCandidate(Candidate candidate) {
        this.candidateId = candidate.candidateId;
        this.email = candidate.email;
        this.diet = candidate.diet;
        return this;
    }
}