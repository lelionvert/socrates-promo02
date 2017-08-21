package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateService;

import java.util.List;
import java.util.Optional;

public class ConfirmationServiceImpl implements ConfirmationService {
    private CandidateService candidateService;
    private ConfirmationRepository confirmationRepository;

    public ConfirmationServiceImpl(CandidateService candidateService, ConfirmationRepository confirmationRepository) {
        this.candidateService = candidateService;
        this.confirmationRepository = confirmationRepository;
    }

    @Override
    public List<Candidate> getListAttendee() {
        return confirmationRepository.getConfirmations();
    }

    @Override
    public boolean confirm(String candidateEmail) {
        Optional<Candidate> foundCandidate = candidateService.findCandidateByEmail(candidateEmail);
        if (foundCandidate.isPresent()) {
            Optional<Candidate> foundConfirmation = confirmationRepository.findConfirmation(foundCandidate.get());
            if (!foundConfirmation.isPresent()) {
                confirmationRepository.add(foundCandidate.get());
                return true;
            }
        }
        return false;
    }

    private boolean hasConfirmation(Candidate candidate) {
        Optional<Candidate> foundCandidate = candidateService.findCandidate(candidate);
        Optional<Candidate> foundConfirmation = confirmationRepository.findConfirmation(candidate);
        return !foundCandidate.isPresent() || foundConfirmation.isPresent();
    }
}
