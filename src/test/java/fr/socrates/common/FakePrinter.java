package fr.socrates.common;

import java.util.ArrayList;
import java.util.List;

public class FakePrinter implements Printer {
    private final List<String> buf;

    public FakePrinter() {
        this.buf = new ArrayList<>();
    }

    @Override
    public void print(String toPrint) {
        buf.add(toPrint);
    }

    @Override
    public void print(List<String> toPrint) {
        buf.addAll(toPrint);
    }

}
