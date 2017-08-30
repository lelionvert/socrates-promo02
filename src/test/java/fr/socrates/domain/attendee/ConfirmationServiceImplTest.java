package fr.socrates.domain.attendee;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateRepository;
import fr.socrates.domain.candidate.exceptions.UnknownCandidateException;
import fr.socrates.infra.storage.repositories.InMemoryConfirmationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class ConfirmationServiceImplTest {
  private CandidateRepository candidateRepository;
  private ConfirmationService confirmationService;

  @Before
  public void setUp() throws Exception {
    candidateRepository = Mockito.mock(CandidateRepository.class);
    confirmationService = new ConfirmationServiceImpl(candidateRepository, new InMemoryConfirmationRepository());
  }

  @Test
  public void should_not_have_any_attendee_by_default() throws Exception {
    assertThat(new ConfirmationServiceImpl(candidateRepository, new InMemoryConfirmationRepository()).getListAttendee()).isEmpty();
  }

  @Test(expected = UnknownCandidateException.class)
  public void should_throw_unknown_candidate_exception_when_confirm_candidate_by_email_who_does_not_exists() throws Exception {
    final String email = "john@doe.fr";
    given(candidateRepository.findCandidateByEmail(email)).willThrow(UnknownCandidateException.class);
    confirmationService.confirm(email);
  }

  @Test
  public void should_confirm_one_existing_candidate_with_his_email() throws Exception {
    final String email = "test@test.fr";
    Mockito.doReturn(Candidate.withEmailAndId(email)).when(candidateRepository).findCandidateByEmail(email);
    Mockito.doReturn(Candidate.withEmailAndId(email)).when(candidateRepository).findCandidateById(new CandidateId(email));
    assertThat(confirmationService.confirm(email)).isTrue();
    assertThat(confirmationService.getListAttendee()).containsExactly(Candidate.withEmailAndId(email));
  }

  @Test
  public void should_not_confirm_a_candidate_twice() throws Exception {
    final String email = "test@test.fr";
    Mockito.doReturn(Candidate.withEmailAndId(email)).when(candidateRepository).findCandidateByEmail(email);
    Mockito.doReturn(Candidate.withEmailAndId(email)).when(candidateRepository).findCandidateById(new CandidateId(email));
    assertThat(confirmationService.confirm(email)).isTrue();
    assertThat(confirmationService.confirm(email)).isFalse();
    assertThat(confirmationService.getListAttendee()).containsExactly(Candidate.withEmailAndId(email));
  }
}
