package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;

import java.util.List;

/**
 * Created by lenovo_13 on 17/08/2017.
 */
public interface ConfirmationService {
    List<Candidate> getListAttendee();
    boolean confirm(Candidate candidate);
}
