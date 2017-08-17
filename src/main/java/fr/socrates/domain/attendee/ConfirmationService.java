package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConfirmationService {
  private List<Candidate> listAttendees;
  private CandidateService candidateService;

  public ConfirmationService(CandidateService candidateService) {
    this.candidateService = candidateService;
    listAttendees = new ArrayList<>();
  }

  public List getListAttendee() {
    return listAttendees;
  }

  public boolean confirm(Candidate candidate) {
    Optional<Candidate> foundCandidate = candidateService.findCandidate(candidate);
    if (foundCandidate.isPresent()) {
      foundCandidate.ifPresent(c -> listAttendees.add(c));
      return true;
    }
    return false;
  }
}
