package fr.socrates.infra.repositories.dao;

import fr.socrates.domain.candidate.Candidate;

class CandidateDao {

  public CandidateDao(Candidate candidate) {
    String id = candidate.getCandidateId().getId();
    String email = candidate.getEmail();
    }
}
