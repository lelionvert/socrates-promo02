package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ConfirmationServiceTest {
  @Test
  public void should_not_have_any_attendee_by_default() throws Exception {
    assertThat(new ConfirmationService().getListAttendee()).isEmpty();
  }

  @Test
  public void should_not_confirm_candidate_who_does_not_exists() throws Exception {
    ConfirmationService confirmationService = new ConfirmationService();
    assertThat(confirmationService.confirm(Candidate.withEmail("john@doe.fr"))).isFalse();
    assertThat(confirmationService.getListAttendee()).isEmpty();
  }
}
