package fr.socrates.domain.sponsor;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Siren {
    private final String siren;

    public Siren(String siren) {
        this.siren = siren;
    }

    public static Siren of(@NotNull String siren) {
        Objects.requireNonNull(siren);
        if (!isSirenValid(siren)) {
            throw new IllegalStateException();
        }
        return new Siren(siren);
    }

    private static boolean isSirenValid(String siren) {
        String sirenWithoutSpaces = siren.replaceAll(" ", "");
        if (sirenWithoutSpaces.length() != 9) {
            return false;
        }

        int total = 0;
        int digit = 0;

        for (int i = 0; i < sirenWithoutSpaces.length(); i++) {
            digit = sirenWithoutSpaces.charAt(i) - 48;
            if ((i % 2) == 1) {
                digit *= 2;
                if (digit > 9) digit -= 9;
            }
            total += digit;
        }
        return (total % 10) == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Siren siren1 = (Siren) o;

        return siren.equals(siren1.siren);
    }

    @Override
    public int hashCode() {
        return siren.hashCode();
    }
}
