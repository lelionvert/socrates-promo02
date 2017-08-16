package fr.socrates;

import fr.socrates.common.Printer;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateRepository;
import fr.socrates.domain.candidate.CandidateService;
import fr.socrates.domain.candidate.CandidateServiceImpl;
import fr.socrates.infra.printers.ConsolePrinter;
import fr.socrates.infra.repositories.InMemoryCandidateRepository;

public class CandidateMain {
  // main argument ==> john@doe.fr petits@pedestres.lcdv
  public static void main(String[] args) {
    CandidateRepository inMemoryCheckInRepository = new InMemoryCandidateRepository();
    Printer consolePrinter = new ConsolePrinter();
    CandidateService candidateService = new CandidateServiceImpl(inMemoryCheckInRepository, consolePrinter);

    for (String argCandidate : args) {
      Candidate candidate = Candidate.withEmail(argCandidate);
      candidateService.add(candidate);
    }

    candidateService.print();
  }
}
