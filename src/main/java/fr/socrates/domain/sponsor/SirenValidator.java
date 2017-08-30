package fr.socrates.domain.sponsor;

public class SirenValidator {
    public SirenValidator() {
    }

    boolean isSirenValid(String siren) {
        int total = 0;
        int digit = 0;

        for (int i = 0; i < siren.length(); i++) {
            if ((i % 2) == 1) {
                digit = Integer.parseInt(String.valueOf(siren.charAt(i))) * 2;
                if (digit > 9) digit -= 9;
            } else digit = Integer.parseInt(String.valueOf(siren.charAt(i)));
            total += digit;
        }
        return (total % 10) == 0;
    }
}