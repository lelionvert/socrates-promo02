package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static fr.socrates.domain.candidate.AccommodationChoices.*;
import static fr.socrates.domain.candidate.AccommodationChoices.AccommodationChoicesBuilder.*;
import static fr.socrates.domain.candidate.Candidate.CandidateBuilder.aCandidate;

public class Candidate {
    private final CandidateId candidateId;
    private final EMail email;
    private final AccommodationChoices accommodationChoices;
    private final ContactInformations contactInformations;

    public EMail getEmail() {
        return email;
    }

    public boolean hasEmail(String email) {
        return this.email.equals(EMail.of(email));
    }

    public boolean hasCandidateID(CandidateId candidateId) {
        return this.candidateId.equals(candidateId);
    }

    private Candidate(CandidateId candidateId, EMail email, ContactInformations contactInformation, AccommodationChoices accommodationChoices) {
        this.candidateId = candidateId;
        this.email = email;
        this.contactInformations = contactInformation;
        this.accommodationChoices = accommodationChoices;
    }

    private Candidate(CandidateId candidateId, EMail email, AccommodationChoices accommodationChoices, PhoneNumber phoneNumber, String twitterAccount, ContactInformations contactInformations) {
        this.candidateId = candidateId;
        this.email = email;
        this.accommodationChoices = accommodationChoices;
        this.contactInformations = contactInformations;
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
                '}';
    }

    public CandidateId getCandidateId() {
        return candidateId;
    }

    public EMail getEmail() {
        return email;
    }

    public String printDetail() {
        throw new NotImplementedException();
    }


    public static final class CandidateBuilder {
        private CandidateId candidateId;
        private EMail email;
        private AccommodationChoices accommodationChoices = AccommodationChoicesBuilder.anAccommodationChoices().build();
        private ContactInformations contactInformations;

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

        public CandidateBuilder withContactInformations(ContactInformations contactInformations) {
            this.contactInformations = contactInformations;
            return this;
        }

        public Candidate build() {
            if (email == null) {
                throw new IllegalStateException("Email is mandatory");
            }
            if (accommodationChoices.getFirstChoice()== null) {
                throw new IllegalStateException("First Choice is mandatory");
            }
            Candidate candidate = new Candidate(candidateId, email, contactInformations, accommodationChoices);
            return candidate;
        }
    }
}
