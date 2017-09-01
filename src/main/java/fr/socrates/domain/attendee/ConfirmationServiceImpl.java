package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateRepository;
import fr.socrates.domain.candidate.exceptions.CandidateException;
import fr.socrates.domain.common.AccommodationChoice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
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
    public boolean confirm(String candidateEmail, LocalDate date, Payment payment, AccommodationChoice accommodationChoice) throws CandidateException {
        final Candidate candidate = candidateRepository.findCandidateByEmail(candidateEmail);
        final boolean confirmationExists = confirmationRepository.confirmationExists(candidate);
        if (confirmationExists) {
            return false;
        }

        confirmationRepository.add(new Confirmation(candidate.getCandidateId(), date, accommodationChoice, payment));
        return true;
    }

    @Override
    public List<Confirmation> getListConfirmations() {
        return confirmationRepository.getConfirmations();
    }
}
