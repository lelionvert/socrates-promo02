package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;
import fr.socrates.infra.repositories.InMemoryCandidateRepository;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ConfirmationServiceTest {

  private InMemoryCandidateRepository inMemoryCandidateRepository;

  @Before
  public void setUp() throws Exception {
    inMemoryCandidateRepository = new InMemoryCandidateRepository();
    inMemoryCandidateRepository.save(Candidate.withEmail("test@test.fr"));
  }

  @Test
  public void should_not_have_any_attendee_by_default() throws Exception {
    assertThat(new ConfirmationService(inMemoryCandidateRepository).getListAttendee()).isEmpty();
  }

  @Test
  public void should_not_confirm_candidate_who_does_not_exists() throws Exception {
    ConfirmationService confirmationService = new ConfirmationService(inMemoryCandidateRepository);
    assertThat(confirmationService.confirm(Candidate.withEmail("john@doe.fr"))).isFalse();
    assertThat(confirmationService.getListAttendee()).isEmpty();
  }

  @Test
  public void should_confirm_one_existing_candidate() throws Exception {
    ConfirmationService confirmationService = new ConfirmationService(inMemoryCandidateRepository);
    assertThat(confirmationService.confirm(Candidate.withEmail("test@test.fr")))
        .isTrue();
    assertThat(confirmationService.getListAttendee()).containsExactly(Candidate.withEmail("test@test.fr"));
  }
}
