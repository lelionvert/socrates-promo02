package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateService;

import java.util.List;
import java.util.Optional;

class ConfirmationServiceImpl implements ConfirmationService {
    private CandidateService candidateService;
    private ConfirmationRepository confirmationRepository;

    ConfirmationServiceImpl(CandidateService candidateService, ConfirmationRepository confirmationRepository) {
        this.candidateService = candidateService;
        this.confirmationRepository = confirmationRepository;
    }

    @Override
    public List<Candidate> getListAttendee() {
        return confirmationRepository.getConfirmations();
    }

    @Override
    public boolean confirm(Candidate candidate) {
        final boolean alreadyConfirmed = hasConfirmation(candidate);
        if (!alreadyConfirmed) {
            confirmationRepository.add(candidate);
        }
        return !alreadyConfirmed;
    }

    private boolean hasConfirmation(Candidate candidate) {
        Optional<Candidate> foundCandidate = candidateService.findCandidate(candidate);
        Optional<Candidate> foundConfirmation = confirmationRepository.findConfirmation(candidate);
        return !foundCandidate.isPresent() || foundConfirmation.isPresent();
    }
}
