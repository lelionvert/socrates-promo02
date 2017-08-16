package fr.socrates.common;

import java.util.List;

public interface Printer {
    void print(String toPrint);

    void print(List<String> toPrint);
}
