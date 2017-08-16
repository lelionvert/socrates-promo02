package fr.socrates.common;

import java.util.ArrayList;
import java.util.List;

public class FakePrinter implements Printer {
    private List<String> buf;

    public FakePrinter() {
        this.buf = new ArrayList<>();
    }

    @Override
    public void print(String toPrint) {
        buf.add(toPrint);
    }

    public String flush() {
        StringBuilder stringToFlush = new StringBuilder();
        for (int i = 0; i < buf.size(); i++) {
            stringToFlush.append(buf.get(i));
            if (i < buf.size() - 1)
                stringToFlush.append("; ");
        }
        return stringToFlush.toString();
    }
}
