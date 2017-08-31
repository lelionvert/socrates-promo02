package fr.socrates.domain.sponsor;

import java.util.function.IntPredicate;
import java.util.function.Predicate;

public enum LuhnValidator implements Predicate<String> {
    SIREN(i -> (i % 2) == 1),
    SIRET(i -> (i % 2) == 0);

    private final IntPredicate predicate;

    LuhnValidator(IntPredicate predicate) {
        this.predicate = predicate;
    }

    public boolean test(String code) {
        int total = 0;
        int digit;

        for (int i = 0; i < code.length(); i++) {
            digit = code.charAt(i) - 48;
            if (predicate.test(i)) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            total += digit;
        }
        return (total % 10) == 0;
    }
}
