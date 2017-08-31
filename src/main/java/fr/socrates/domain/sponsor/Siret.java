package fr.socrates.domain.sponsor;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Siret {
    private final String siret;

    public Siret(String siret) {
        this.siret = siret;
    }

    public static Siret of(@NotNull String siret) {
        Objects.requireNonNull(siret);
        if (!isSiretSyntaxValid(siret)) {
            throw new IllegalStateException("Siret is invalid : " + siret);
        }
        return new Siret(siret);
    }

    private static boolean isSiretSyntaxValid(String siret) {
        String siretWithoutSpaces = siret.replaceAll(" ", "");
        if (siretWithoutSpaces.length() != 14) {
            return false;
        }

        int total = 0;
        int digit;

        for (int i = 0; i < siretWithoutSpaces.length(); i++) {
            digit = siretWithoutSpaces.charAt(i) - 48;
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

    public Siren toSiren() {
        return Siren.of(this.siret.substring(0, 9));
    }
}
