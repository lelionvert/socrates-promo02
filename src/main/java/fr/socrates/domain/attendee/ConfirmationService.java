package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.common.AccommodationChoice;
import fr.socrates.domain.meal.Diets;

import java.time.LocalDate;
import java.util.List;

public interface ConfirmationService {
    List<Candidate> getListAttendee();

    boolean confirm(String candidateEmail, LocalDate date, Payment transfer, AccommodationChoice singleRoom);
    List<Confirmation> getListConfirmations();

    void addDiet(String candidateEmail);

    void addDiet(String candidateEmail, Diets diets);
}
