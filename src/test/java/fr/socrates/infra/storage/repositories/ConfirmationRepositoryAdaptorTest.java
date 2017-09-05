package fr.socrates.infra.storage.repositories;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.attendee.Confirmation;
import fr.socrates.domain.attendee.ConfirmationRepository;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.infra.storage.entities.CandidateEntity;
import fr.socrates.infra.storage.entities.ConfirmationEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ConfirmationRepositoryAdaptorTest {
    @Mock
    JpaCandidateRepository jpaCandidateRepository;

    @Mock
    JpaConfirmationRepository jpaConfirmationRepository;

    private ConfirmationRepository confirmationRepository;
    private Date confirmationDate;
    private LocalDate confirmationLocalDate;
    private CandidateEntity candidateEntity;

    @Before
    public void setUp() throws Exception {
        confirmationRepository = new ConfirmationRepositoryAdaptor(jpaConfirmationRepository, jpaCandidateRepository);
        confirmationLocalDate = LocalDate.of(2017, 8, 31);
        confirmationDate = Date.from(confirmationLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Test
    public void should_add_a_confirmation_of_a_candidate() throws Exception {
        // GIVEN
        CandidateEntity candidate = new CandidateEntity(1L, "mail@server.fr");
        candidate.setCandidateId("mail@server.fr");
        given(jpaCandidateRepository.findByCandidateId("mail@server.fr")).willReturn(candidate);
        // WHEN
        Confirmation candidateConfirmation = new Confirmation(new CandidateId(candidate.getCandidateId()), confirmationLocalDate);
        confirmationRepository.add(candidateConfirmation);
        // THEN
        ConfirmationEntity confirmation = ConfirmationEntity.fromDomain(candidateConfirmation, candidate);
        verify(jpaConfirmationRepository).save(confirmation);
    }

    @Test
    public void should_list_all_confirmations_of_candidates() throws Exception {
        // GIVEN
        CandidateEntity candidate = new CandidateEntity(1L, "john@doe.fr");
        candidate.setCandidateId("john@doe.fr");
        ConfirmationEntity confirmationEntity = new ConfirmationEntity();
        confirmationEntity.setCandidate(candidate);
        confirmationEntity.setConfirmationDate(confirmationDate);
        given(jpaConfirmationRepository.findAll()).willReturn(Collections.singletonList(confirmationEntity));
        // WHEN
        List<Confirmation> confirmations = confirmationRepository.getConfirmations();
        // THEN
        verify(jpaConfirmationRepository).findAll();
        assertThat(confirmations).containsExactly(confirmationEntity.toDomain());
    }

    @Test
    public void should_confirm_that_confirmation_exists() throws Exception {
        CandidateEntity candidate = new CandidateEntity(1L, "john@doe.fr");
        candidate.setCandidateId("john@doe.fr");
        ConfirmationEntity confirmationEntity = new ConfirmationEntity();
        confirmationEntity.setCandidate(candidate);
        confirmationEntity.setConfirmationDate(confirmationDate);
        given(jpaConfirmationRepository.findByCandidateCandidateId("john@doe.fr")).willReturn(confirmationEntity);

        boolean doesConfirmationExists = confirmationRepository.confirmationExists(candidate.toDomain());

        verify(jpaConfirmationRepository).findByCandidateCandidateId("john@doe.fr");
        assertThat(doesConfirmationExists).isTrue();
    }

    @Test
    public void should_have_no_confirmation_for_a_new_candidate() throws Exception {
        final Candidate newCandidate = Candidate.singleRoomWithEmail("test");
        given(jpaConfirmationRepository.findByCandidateCandidateId("test")).willReturn(null);

        final boolean isCandidateConfirmed = confirmationRepository.confirmationExists(newCandidate);

        verify(jpaConfirmationRepository).findByCandidateCandidateId("test");
        assertThat(isCandidateConfirmed).isFalse();
    }

    private Candidate createCandidate(CandidateEntity candidateEntity) {
        return Candidate.singleRoomWithEmail(candidateEntity.getEmail());
    }

    private Confirmation createConfirmation(CandidateEntity candidate) {
        Confirmation candidateConfirmation = new Confirmation(new CandidateId(candidate.getCandidateId()), confirmationLocalDate);
        confirmationRepository.add(candidateConfirmation);
        return candidateConfirmation;
    }

    private ConfirmationEntity getConfirmationEntity() {
        ConfirmationEntity confirmationEntity = new ConfirmationEntity();
        confirmationEntity.setCandidate(candidateEntity);
        confirmationEntity.setConfirmationDate(confirmationDate);
        return confirmationEntity;
    }

    private CandidateEntity createCandidateEntity(int unicityNumber) {
        CandidateEntity candidate = new CandidateEntity();
        candidate.setEmail(unicityNumber + "mail@server.fr");
        candidate.setCandidateId(unicityNumber + "mail@server.fr");
        jpaCandidateRepository.save(candidate);
        return candidate;
    }
}
