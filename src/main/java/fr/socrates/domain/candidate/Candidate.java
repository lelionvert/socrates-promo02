package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;

public class Candidate {
    private final CandidateId candidateId;
    private final EMail email;

    public EMail getEmail() {
        return email;
    }

    public boolean hasEmail(String email) {
        return this.email.equals(EMail.of(email));
    }

    public boolean hasCandidateID(CandidateId candidateId) {
        return this.candidateId.equals(candidateId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Candidate candidate = (Candidate) o;

        return candidateId.equals(candidate.candidateId);
    }

    @Override
    public int hashCode() {
        return candidateId.hashCode();
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "candidateId=" + candidateId +
                ", email=" + email +
                '}';
    }

    private Candidate(CandidateId candidateId, EMail email) {
        this.candidateId = candidateId;
        this.email = email;
    }

    public CandidateId getCandidateId() {
        return candidateId;
    }

    public static Candidate withEmail(String email) {
        if (email == null) {
            throw new IllegalStateException();
        }
        return new Candidate(new CandidateId(email), EMail.of(email));

    }
}
