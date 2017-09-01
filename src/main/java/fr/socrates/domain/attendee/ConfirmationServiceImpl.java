package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateRepository;
import fr.socrates.domain.common.AccommodationChoice;
import fr.socrates.domain.meal.Diet;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConfirmationServiceImpl implements ConfirmationService {
    private CandidateRepository candidateRepository;
    private ConfirmationRepository confirmationRepository;

    public ConfirmationServiceImpl(CandidateRepository candidateRepository, ConfirmationRepository confirmationRepository) {
        this.candidateRepository = candidateRepository;
        this.confirmationRepository = confirmationRepository;
    }

    @Override
    public List<Candidate> getListAttendee() {
        final List<Candidate> attendeesList = new ArrayList<>();
        final List<Confirmation> confirmationsList = confirmationRepository.getConfirmations();
        for (Confirmation confirmation : confirmationsList) {
            Optional<Candidate> foundCandidate = candidateRepository.findByCandidateID(confirmation.getCandidateId());
            foundCandidate.ifPresent(attendeesList::add);
        }
        return attendeesList;

    }

    @Override
    public boolean confirm(String candidateEmail, LocalDate date, Payment payment, AccommodationChoice accommodationChoice) {
        Optional<Candidate> foundCandidate = candidateRepository.findByEmail(candidateEmail);
        if (!foundCandidate.isPresent())
            return false;

        final Candidate candidate = foundCandidate.get();
        final boolean confirmationExists = confirmationRepository.confirmationExists(candidate);
        if (confirmationExists)
            return false;

        confirmationRepository.add(new Confirmation(candidate.getCandidateId(), date, accommodationChoice, payment));
        return true;
    }

    @Override
    public List<Confirmation> getListConfirmations() {
        return confirmationRepository.getConfirmations();
    }

    @Override
    public void addDiet(String candidateEmail, Diet diet) {
        Optional<Candidate> foundCandidate = candidateRepository.findByEmail(candidateEmail);

        foundCandidate
                .filter(confirmationRepository::confirmationExists)
                .ifPresent(candidate -> candidateRepository.updateDietOf(candidate.getCandidateId(), diet));
    }
}
