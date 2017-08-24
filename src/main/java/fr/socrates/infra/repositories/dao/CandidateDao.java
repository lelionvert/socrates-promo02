package fr.socrates.infra.repositories.dao;

import fr.socrates.domain.candidate.Candidate;

public class CandidateDao {
    private String id;
    private String email;

    public CandidateDao(Candidate candidate) {
        this.id = candidate.getCandidateId().getId();
        this.email = candidate.getEmail().getEmail();
    }
}
