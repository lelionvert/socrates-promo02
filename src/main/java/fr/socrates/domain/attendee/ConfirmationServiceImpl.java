package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateService;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        final List<Candidate> attendeesList = new ArrayList<>();
        final List<Confirmation> confirmationsList = confirmationRepository.getRealConfirmations();
        for (Confirmation confirmation : confirmationsList)
        {
            Optional<Candidate> foundCandidate = candidateService.findCandidateByCandidateID(confirmation.getCandidateId());
            if (foundCandidate.isPresent()) {
                attendeesList.add(foundCandidate.get());
            }
        }
            return attendeesList;

    }

    @Override
    public boolean confirm(String candidateEmail) {
        Optional<Candidate> foundCandidate = candidateService.findCandidateByEmail(candidateEmail);
        if (foundCandidate.isPresent()) {
            final Candidate candidate = foundCandidate.get();
            Optional<Candidate> foundConfirmation = confirmationRepository.findConfirmation(candidate);
            if (!foundConfirmation.isPresent()) {
                confirmationRepository.add(candidate);
                confirmationRepository.add(new Confirmation(candidate.getCandidateId(), LocalDateTime.now()));
                return true;
            }
        }
        return false;
    }
}
