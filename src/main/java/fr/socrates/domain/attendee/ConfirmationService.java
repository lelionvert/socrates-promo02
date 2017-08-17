package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateService;

import java.util.List;
import java.util.Optional;

public class ConfirmationService {
  private CandidateService candidateService;
  private ConfirmationRepository confirmationRepository;

  public ConfirmationService(CandidateService candidateService, ConfirmationRepository confirmationRepository) {
    this.candidateService = candidateService;
    this.confirmationRepository = confirmationRepository;
  }

  public List getListAttendee() {
    return confirmationRepository.getConfirmations();
  }

  public boolean confirm(Candidate candidate) {
    Optional<Candidate> foundCandidate = candidateService.findCandidate(candidate);
    if (foundCandidate.isPresent()) {
      foundCandidate.ifPresent(c -> confirmationRepository.add(c));
      return true;
    }
    return false;
  }
}
