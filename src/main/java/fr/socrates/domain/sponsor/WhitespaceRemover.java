package fr.socrates.domain.sponsor;

import java.util.function.UnaryOperator;

public class WhitespaceRemover implements UnaryOperator<String> {
    @Override
    public String apply(String s) {
        return s.replaceAll(" ", "");
    }
}
