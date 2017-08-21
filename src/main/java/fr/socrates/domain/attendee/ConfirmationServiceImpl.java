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

    List<Candidate> getListAttendee() {
        return confirmationRepository.getConfirmations();
    }

    boolean confirm(Candidate candidate) {
        Optional<Candidate> foundCandidate = candidateService.findCandidate(candidate);
        if (foundCandidate.isPresent()) {
            Optional<Candidate> foundConfirmation = confirmationRepository.findConfirmation(foundCandidate.get());
            if (!foundConfirmation.isPresent()) {
                confirmationRepository.add(candidate);
                return true;
            }
        }
        return false;
    }
}
