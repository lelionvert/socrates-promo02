package fr.socrates.domain.candidate;

import java.util.List;

class CandidateService {
    private final CandidateConnector connector;

    CandidateService(CandidateConnector connector) {
        this.connector = connector;
    }

    List<Candidate> getList() {
        return connector.getList();
    }

    void add(Candidate candidate) {
        connector.add(candidate);
    }
}
