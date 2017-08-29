package fr.socrates.domain.candidate;

public class ContactInformation {
    private String twitter;
    private PhoneNumber phoneNumber;

    private ContactInformation(String twitter, PhoneNumber phoneNumber) {
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

        public ContactInformation build() {
            ContactInformation contactInformation = new ContactInformation(twitter, phoneNumber);
            return contactInformation;
        }
    }

    @Override
    public String toString() {
        return "ContactInformation{" +
                "twitter='" + twitter + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
