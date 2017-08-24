package fr.socrates.domain.attendee;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateRepository;
import fr.socrates.infra.repositories.InMemoryConfirmationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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

  @Test
  public void should_not_confirm_candidate_by_email_who_does_not_exists() throws Exception {
    final String email = "john@doe.fr";
    Mockito.doReturn(Optional.empty()).when(candidateRepository).findByEmail(email);
    assertThat(confirmationService.confirm(email, Accommodation.SINGLE_ROOM, Payment.TRANSFER)).isFalse();
    assertThat(confirmationService.getListAttendee()).isEmpty();
  }

  @Test
  public void should_confirm_one_existing_candidate_with_his_email() throws Exception {
    final String email = "test@test.fr";
    Mockito.doReturn(Optional.of(Candidate.singleRoomWithEmail(email))).when(candidateRepository).findByEmail(email);
    Mockito.doReturn(Optional.of(Candidate.singleRoomWithEmail(email))).when(candidateRepository).findByCandidateID(new CandidateId(email));
    assertThat(confirmationService.confirm(email,  Accommodation.SINGLE_ROOM, Payment.TRANSFER)).isTrue();
    assertThat(confirmationService.getListAttendee()).containsExactly(Candidate.singleRoomWithEmail(email));
  }

  @Test
  public void should_not_confirm_a_candidate_twice() throws Exception {
    final String email = "test@test.fr";
    Mockito.doReturn(Optional.of(Candidate.singleRoomWithEmail(email))).when(candidateRepository).findByEmail(email);
    Mockito.doReturn(Optional.of(Candidate.singleRoomWithEmail(email))).when(candidateRepository).findByCandidateID(new CandidateId(email));
    assertThat(confirmationService.confirm(email, Accommodation.SINGLE_ROOM, Payment.TRANSFER)).isTrue();
    assertThat(confirmationService.confirm(email, Accommodation.SINGLE_ROOM, Payment.TRANSFER)).isFalse();
    assertThat(confirmationService.getListAttendee()).containsExactly(Candidate.singleRoomWithEmail(email));
  }
}
