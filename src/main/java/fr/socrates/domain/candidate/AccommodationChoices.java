package fr.socrates.domain.candidate;

import java.util.Arrays;

public class AccommodationChoices {

    public static final int MAX_CHOICES = 4;
    private AccommodationChoice[] accommodationChoices;
    private String remarks;

    private AccommodationChoices(AccommodationChoice[] accommodationChoices, String remarks) {
        this.accommodationChoices = accommodationChoices;
        this.remarks = remarks;
    }


    public AccommodationChoice getFirstChoice() {
        return accommodationChoices[0];
    }

    public static final class AccommodationChoicesBuilder {

        private AccommodationChoice[] accommodationChoices = new AccommodationChoice[MAX_CHOICES];
        private String remarks;
        private AccommodationChoicesBuilder() {
        }

        public static AccommodationChoicesBuilder anAccommodationChoices() {
            return new AccommodationChoicesBuilder();
        }

        public AccommodationChoicesBuilder withAccommodationChoices(AccommodationChoice... accommodationChoices) {
            this.accommodationChoices = accommodationChoices;
            return this;
        }

        public AccommodationChoicesBuilder withRemarks(String remarks) {
            this.remarks = remarks;
            return this;
        }

        public AccommodationChoices build() {
            return new AccommodationChoices(accommodationChoices, remarks);
        }

        public AccommodationChoicesBuilder withFirstChoice(AccommodationChoice firstChoice) {
            accommodationChoices[0] = firstChoice;
            return this;
        }

    }

    @Override
    public String toString() {
        return "AccommodationChoices{" +
                "accommodationChoices=" + Arrays.toString(accommodationChoices) +
                ", remarks='" + remarks + '\'' +
                '}';
    }

}
