package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConfirmationService {
  private CandidateRepository candidateRepository;
  private List<Candidate> listAttendees;

  public ConfirmationService(CandidateRepository candidateRepository) {
    this.candidateRepository = candidateRepository;
    listAttendees = new ArrayList<>();
  }

  public List getListAttendee() {
    return listAttendees;
  }

  public boolean confirm(Candidate candidate) {
    List<Candidate> foundCandidates = candidateRepository.findAll().stream().filter(e -> e.equals(candidate)).collect(Collectors.toList());
    if (foundCandidates.size() > 0) {
      listAttendees.add(foundCandidates.get(0));
      return true;
    }
    return false;
  }
}
