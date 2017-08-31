package fr.socrates.domain.sponsor;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class Siren {
    private final String siren;

    public Siren(@NotNull String siren) {
        this.siren = Optional.of(siren)
                .map(new WhitespaceRemover())
                .filter(this::hasRightLength)
                .filter(LuhnValidator.SIREN)
                .orElseThrow(IllegalStateException::new);
    }

    private boolean hasRightLength(String siren) {
        return siren.length() == 9;
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
