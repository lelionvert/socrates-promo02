package fr.socrates.domain.candidate;

public class PhoneNumber {
    private String phoneNumber;

    private PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static PhoneNumber of(String phoneNumber){
        return new PhoneNumber(phoneNumber);
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
