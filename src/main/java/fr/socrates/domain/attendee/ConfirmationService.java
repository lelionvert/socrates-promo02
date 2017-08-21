package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;

import java.util.List;

public interface ConfirmationService {
    List<Candidate> getListAttendee();
    boolean confirm(String candidateEmail);
}
