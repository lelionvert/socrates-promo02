package fr.socrates.infra.printers;

import fr.socrates.common.Printer;

import java.util.List;

public class ConsolePrinter implements Printer {
  @Override
  public void print(String toPrint) {
    System.out.println(toPrint);
  }

  @Override
  public void print(List<String> listToPrint) {
    listToPrint.forEach(System.out::println);
  }
}
