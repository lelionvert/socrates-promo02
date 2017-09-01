package fr.socrates.domain.attendee;

import fr.socrates.domain.attendee.exceptions.UnknownConfirmationException;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.exceptions.CandidateException;
import fr.socrates.domain.candidate.CandidateRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConfirmationServiceImpl implements ConfirmationService {
    private CandidateRepository candidateRepository;
    private ConfirmationRepository confirmationRepository;

    public ConfirmationServiceImpl(CandidateRepository candidateRepository, ConfirmationRepository confirmationRepository) {
        this.candidateRepository = candidateRepository;
        this.confirmationRepository = confirmationRepository;
    }

    @Override
    public List<Candidate> getListAttendee() throws CandidateException {
        final List<Candidate> attendeesList = new ArrayList<>();
        final List<Confirmation> confirmationsList = confirmationRepository.getConfirmations();
        for (Confirmation confirmation : confirmationsList) {
            attendeesList.add(candidateRepository.findCandidateById(confirmation.getCandidateId()));
        }
        return attendeesList;
    }

    @Override
    public boolean confirm(String candidateEmail) throws CandidateException, UnknownConfirmationException {
        final Candidate candidate = candidateRepository.findCandidateByEmail(candidateEmail);
        final boolean confirmationExists = confirmationRepository.confirmationExists(candidate);
        if (confirmationExists) {
            return false;
        }

        confirmationRepository.add(new Confirmation(candidate.getCandidateId(), LocalDateTime.now()));
        return true;
    }
}
