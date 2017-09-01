package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.common.AccommodationChoice;
import fr.socrates.domain.meal.Diet;

import java.time.LocalDate;
import java.util.List;

public interface ConfirmationService {
    List<Candidate> getAttendee();

    boolean confirm(String candidateEmail, LocalDate date, Payment transfer, AccommodationChoice singleRoom);

    List<Confirmation> getConfirmations();

    void addDiet(String candidateEmail, Diet diet);
}
