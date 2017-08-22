package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;

public class Candidate {
    private final CandidateId candidateId;
    private final EMail email;
    private final AccommodationChoice[] accommodationChoices;
    private final PhoneNumber phoneNumber;
    private final String twitterAccount;

    public EMail getEmail() {
        return email;
    }

    public boolean hasEmail(String email) {
        return this.email.equals(EMail.of(email));
    }

    public boolean hasCandidateID(CandidateId candidateId) {
        return this.candidateId.equals(candidateId);
    }

    private Candidate(CandidateId candidateId, EMail email) {
        this.candidateId = candidateId;
        this.email = email;
        this.accommodationChoices = new AccommodationChoice[4];
        phoneNumber = null;
        twitterAccount = null;
    }

    private Candidate(CandidateId candidateId, EMail email, AccommodationChoice[] accommodationChoices, PhoneNumber phoneNumber, String twitterAccount) {
        this.candidateId = candidateId;
        this.email = email;
        this.accommodationChoices = accommodationChoices;
        this.phoneNumber = phoneNumber;
        this.twitterAccount = twitterAccount;
    }

    public static Candidate withEmail(String email) {
        if (email == null) {
            throw new IllegalStateException();
        }
        return new Candidate(new CandidateId(email), EMail.of(email));
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

    public CandidateId getCandidateId() {
        return candidateId;
    }

    public static final class CandidateBuilder {
        private EMail email;
        private AccommodationChoice[] accommodationChoices;
        private PhoneNumber phoneNumber;
        private String twitterAccount;

        private CandidateBuilder() {
            accommodationChoices = new AccommodationChoice[4];
        }

        public static CandidateBuilder aCandidate() {
            return new CandidateBuilder();
        }

        public CandidateBuilder withEmail(EMail email) {
            this.email = email;
            return this;
        }

        public CandidateBuilder withAccommodationChoices(AccommodationChoice[] accommodationChoices) {
            // TODO rework Accommodation choices
            this.accommodationChoices = accommodationChoices;
            return this;
        }

        public CandidateBuilder withPhoneNumber(PhoneNumber phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public CandidateBuilder withTwitterAccount(String twitterAccount) {
            this.twitterAccount = twitterAccount;
            return this;
        }

        public Candidate build() {
            if (accommodationChoices[0] == null) {
                throw new IllegalStateException("First Choice is mandatory");
            }
            Candidate candidate = new Candidate(email, accommodationChoices, phoneNumber, twitterAccount);
            return candidate;
        }
    }
}
