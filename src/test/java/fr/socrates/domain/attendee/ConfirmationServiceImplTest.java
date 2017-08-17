package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateService;
import fr.socrates.domain.candidate.CandidateServiceImpl;
import fr.socrates.infra.repositories.InMemoryConfirmationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfirmationServiceImplTest {
  private CandidateService candidateService;

  @Before
  public void setUp() throws Exception {
    candidateService = Mockito.spy(new CandidateServiceImpl(null, null));
  }

  @Test
  public void should_not_have_any_attendee_by_default() throws Exception {
    assertThat(new ConfirmationServiceImpl(candidateService, new InMemoryConfirmationRepository()).getListAttendee()).isEmpty();
  }

  @Test
  public void should_not_confirm_candidate_who_does_not_exists() throws Exception {
    Mockito.doReturn(Optional.empty()).when(candidateService).findCandidate(Candidate.withEmail("john@doe.fr"));
    ConfirmationServiceImpl confirmationServiceImpl = new ConfirmationServiceImpl(candidateService, new InMemoryConfirmationRepository());
    assertThat(confirmationServiceImpl.confirm(Candidate.withEmail("john@doe.fr"))).isFalse();
    assertThat(confirmationServiceImpl.getListAttendee()).isEmpty();
  }

  @Test
  public void should_confirm_one_existing_candidate() throws Exception {
    Mockito.doReturn(Optional.of(Candidate.withEmail("test@test.fr"))).when(candidateService).findCandidate(Candidate.withEmail("test@test.fr"));
    ConfirmationServiceImpl confirmationServiceImpl = new ConfirmationServiceImpl(candidateService, new InMemoryConfirmationRepository());
    assertThat(confirmationServiceImpl.confirm(Candidate.withEmail("test@test.fr")))
        .isTrue();
    assertThat(confirmationServiceImpl.getListAttendee()).containsExactly(Candidate.withEmail("test@test.fr"));
  }
}
