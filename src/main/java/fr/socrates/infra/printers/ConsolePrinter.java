package fr.socrates.infra.printers;

import fr.socrates.common.Printer;

public class ConsolePrinter implements Printer {
  @Override
  public void print(String toPrint) {
    System.out.println(toPrint);
  }
}
