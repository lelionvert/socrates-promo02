package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateService;
import fr.socrates.infra.repositories.InMemoryConfirmationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfirmationServiceImplTest {
  private CandidateService candidateService;
  private ConfirmationService confirmationService;

  @Before
  public void setUp() throws Exception {
    candidateService = Mockito.mock(CandidateService.class);
    confirmationService = new ConfirmationServiceImpl(candidateService, new InMemoryConfirmationRepository());
  }

  @Test
  public void should_not_have_any_attendee_by_default() throws Exception {
    assertThat(new ConfirmationServiceImpl(candidateService, new InMemoryConfirmationRepository()).getListAttendee()).isEmpty();
  }

  @Test
  public void should_not_confirm_candidate_by_email_who_does_not_exists() throws Exception {
    final String email = "john@doe.fr";
    Mockito.doReturn(Optional.empty()).when(candidateService).findCandidateByEmail(email);
    assertThat(confirmationService.confirm(email)).isFalse();
    assertThat(confirmationService.getListAttendee()).isEmpty();
  }

  @Test
  public void should_confirm_one_existing_candidate_with_his_email() throws Exception {
    final String email = "test@test.fr";
    Mockito.doReturn(Optional.of(Candidate.withEmail(email))).when(candidateService).findCandidateByEmail(email);
    assertThat(confirmationService.confirm(email)).isTrue();
    assertThat(confirmationService.getListAttendee()).containsExactly(Candidate.withEmail(email));
  }

  @Test
  public void should_not_confirm_a_candidate_twice() throws Exception {
    final String email = "test@test.fr";
    Mockito.doReturn(Optional.of(Candidate.withEmail(email))).when(candidateService).findCandidateByEmail(email);
    assertThat(confirmationService.confirm(email)).isTrue();
    assertThat(confirmationService.confirm(email)).isFalse();
    assertThat(confirmationService.getListAttendee()).containsExactly(Candidate.withEmail(email));
  }
}
