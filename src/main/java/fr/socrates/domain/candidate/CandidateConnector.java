package fr.socrates.domain.candidate;

import java.util.List;

interface CandidateConnector {

    List<Candidate> getList();

    void add(Candidate candidate);
}
