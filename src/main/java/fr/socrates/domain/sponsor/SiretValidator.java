package fr.socrates.domain.sponsor;

public class SiretValidator {
    public SiretValidator() {
    }

    boolean isSiretSyntaxValide(String siret) {
        if (siret == null) {
            return false;
        }
        String siretWithoutSpaces = siret.replaceAll(" ", "");
        if (siretWithoutSpaces.length() != 14) {
            return false;
        }

        int total = 0;
        int digit;

        for (int i = 0; i < siretWithoutSpaces.length(); i++) {

            if ((i % 2) == 0) {
                digit = Integer.parseInt(String.valueOf(siretWithoutSpaces.charAt(i))) * 2;
                if (digit > 9) digit -= 9;
            } else digit = Integer.parseInt(String.valueOf(siretWithoutSpaces.charAt(i)));
            total += digit;
        }
        return (total % 10) == 0;
    }
}