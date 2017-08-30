package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.common.AccommodationChoice;
import fr.socrates.domain.common.AccommodationChoices;

import static fr.socrates.domain.common.AccommodationChoices.*;
import static fr.socrates.domain.common.AccommodationChoices.AccommodationChoicesBuilder.*;
import static fr.socrates.domain.candidate.Candidate.CandidateBuilder.aCandidate;

public class Candidate {
    private final CandidateId candidateId;
    private final EMail email;
    private final AccommodationChoices accommodationChoices;
    private final ContactInformation contactInformation;
    private ContactInformation contactInformations;

    public boolean hasEmail(String email) {
        return this.email.equals(EMail.of(email));
    }

    public boolean hasCandidateID(CandidateId candidateId) {
        return this.candidateId.equals(candidateId);
    }

    private Candidate(CandidateId candidateId, EMail email, ContactInformation contactInformation, AccommodationChoices accommodationChoices) {
        this.candidateId = candidateId;
        this.email = email;
        this.contactInformation = contactInformation;
        this.accommodationChoices = accommodationChoices;
    }

    private Candidate(CandidateId candidateId, EMail email, AccommodationChoices accommodationChoices, PhoneNumber phoneNumber, String twitterAccount, ContactInformation contactInformation) {
        this.candidateId = candidateId;
        this.email = email;
        this.accommodationChoices = accommodationChoices;
        this.contactInformation = contactInformation;
    }

    public static Candidate singleRoomWithEmail(String email) {
        return aCandidate()
                .withCandidateId(new CandidateId(email))
                .withEmail(EMail.of(email))
                .withAccommodationChoices(
                        anAccommodationChoices()
                                .withAccommodationChoices(AccommodationChoice.SINGLE_ROOM).build()).build();

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
                ", accommodationChoices=" + accommodationChoices +
                ", contactInformation=" + contactInformation +
                '}';
    }

    public CandidateId getCandidateId() {
        return candidateId;
    }

    public EMail getEmail() {
        return email;
    }

    public String printDetail() {
        return this.toString();
    }

    public AccommodationChoices getAccommodationChoices() {
        return accommodationChoices;
    }

    public ContactInformation getContactInformations() {
        return contactInformations;
    }

    public static final class CandidateBuilder {
        private CandidateId candidateId;
        private EMail email;
        private AccommodationChoices accommodationChoices = AccommodationChoicesBuilder.anAccommodationChoices().build();
        private ContactInformation contactInformation;

        private CandidateBuilder() {
        }

        public static CandidateBuilder aCandidate() {
            return new CandidateBuilder();
        }

        public CandidateBuilder withCandidateId(CandidateId candidateId) {
            this.candidateId = candidateId;
            return this;
        }

        public CandidateBuilder withEmail(EMail email) {
            this.email = email;
            return this;
        }

        public CandidateBuilder withAccommodationChoices(AccommodationChoices accommodationChoices) {
            this.accommodationChoices = accommodationChoices;
            return this;
        }

        public CandidateBuilder withOneAccommodationChoice(AccommodationChoice accommodationChoice) {
            this.accommodationChoices = AccommodationChoicesBuilder
                    .anAccommodationChoices()
                    .withAccommodationChoices(accommodationChoice).build();
            return this;
        }

        public CandidateBuilder withContactInformations(ContactInformation contactInformation) {
            this.contactInformation = contactInformation;
            return this;
        }

        public Candidate build() {
            if (email == null) {
                throw new IllegalStateException("Email is mandatory");
            }
            if (accommodationChoices.getFirstChoice()== null) {
                throw new IllegalStateException("First Choice is mandatory");
            }
            return new Candidate(candidateId, email, contactInformation, accommodationChoices);
        }
    }
}
