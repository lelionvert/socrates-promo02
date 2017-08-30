package fr.socrates.domain.sponsor;

import javax.validation.constraints.NotNull;

public class SiretValidator {

    private static final int CHAR_0 = 48;

    public SiretValidator() {
    }

    boolean isSiretSyntaxValid(@NotNull String siret) {
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
            digit = charToInt(siretWithoutSpaces.charAt(i));
            if ((i % 2) == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            total += digit;
        }
        return (total % 10) == 0;
    }

    private int charToInt(Character character) {
        return character - CHAR_0;
    }
}