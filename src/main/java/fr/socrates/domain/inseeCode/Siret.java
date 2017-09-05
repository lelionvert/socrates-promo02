package fr.socrates.domain.inseeCode;

import fr.socrates.common.WhitespaceRemover;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class Siret {
    private final Siren siren;
    private final String nic;

    public Siret(@NotNull String siret) {
        final Optional<String> siretWithRightLength = Optional.of(siret)
                .map(new WhitespaceRemover())
                .filter(this::hasRightLength)
                .filter(new LuhnValidator());

        this.siren = siretWithRightLength
                .map(this::extractSiren)
                .map(Siren::new)
                .orElseThrow(() -> new IllegalStateException("Invalid SIRET : " + siret));
        this.nic = siretWithRightLength
                .map(this::extractNic)
                .orElseThrow(() -> new IllegalStateException("Invalid SIRET : " + siret));
    }

    private boolean hasRightLength(String s) {
        return s.length() == 14;
    }

    private String extractNic(String siret) {
        return siret.substring(9);
    }

    private String extractSiren(String siret) {
        return siret.substring(0, 9);
    }

    public Siren getSiren() {
        return this.siren;
    }
}
