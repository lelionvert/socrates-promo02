package fr.socrates.domain.candidate;

public class ContactInformations {
    private String twitter;
    private PhoneNumber phoneNumber;

    private ContactInformations(String twitter, PhoneNumber phoneNumber) {
        this.twitter = twitter;
        this.phoneNumber = phoneNumber;
    }

    public static final class ContactInformationsBuilder {
        private String twitter;
        private PhoneNumber phoneNumber;

        private ContactInformationsBuilder() {
        }

        public static ContactInformationsBuilder aContactInformations() {
            return new ContactInformationsBuilder();
        }

        public ContactInformationsBuilder withTwitter(String twitter) {
            this.twitter = twitter;
            return this;
        }

        public ContactInformationsBuilder withPhoneNumber(PhoneNumber phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public ContactInformations build() {
            ContactInformations contactInformations = new ContactInformations(twitter, phoneNumber);
            return contactInformations;
        }
    }
}
