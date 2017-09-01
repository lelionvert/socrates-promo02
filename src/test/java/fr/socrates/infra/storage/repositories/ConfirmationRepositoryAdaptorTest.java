package fr.socrates.infra.storage.repositories;

import fr.socrates.SocratesApplication;
import fr.socrates.domain.CandidateId;
import fr.socrates.domain.attendee.Confirmation;
import fr.socrates.domain.attendee.ConfirmationRepository;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.infra.storage.entities.CandidateEntity;
import fr.socrates.infra.storage.entities.ConfirmationEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SocratesApplication.class})
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ConfirmationRepositoryAdaptorTest {
    @Autowired
    JpaCandidateRepository jpaCandidateRepository;

    @Autowired
    JpaConfirmationRepository jpaConfirmationRepository;
    private ConfirmationRepository confirmationRepository;
    private Date canfirmationDate;
    private LocalDate confirmationLocalDate;
    private CandidateEntity candidateEntity;

    @Before
    public void setUp() throws Exception {
        confirmationRepository = new ConfirmationRepositoryAdaptor(jpaConfirmationRepository, jpaCandidateRepository);
        confirmationLocalDate = LocalDate.of(2017, 8, 31);
        canfirmationDate = Date.from(confirmationLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @After
    public void tearDown() throws Exception {
        jpaConfirmationRepository.deleteAll();
        jpaCandidateRepository.deleteAll();
    }

    @Test
    public void should_add_a_confirmation_of_a_candidate() throws Exception {
        candidateEntity = createCandidateEntity(1);
        createConfirmation(candidateEntity);
        ConfirmationEntity confirmationEntity = getConfirmationEntity();
        List<ConfirmationEntity> all = jpaConfirmationRepository.findAll();
        assertThat(all).containsExactly(confirmationEntity);
    }

    @Test
    public void should_list_all_confirmations_of_candidates() throws Exception {
        candidateEntity = createCandidateEntity(2);
        final Confirmation confirmation = createConfirmation(candidateEntity);
        assertThat(confirmationRepository.getConfirmations()).containsExactly(confirmation);

    }

    @Test
    public void should_confirm_that_confirmation_exists() throws Exception {
        candidateEntity = createCandidateEntity(3);
        final Confirmation confirmation = createConfirmation(candidateEntity);
        assertThat(confirmationRepository.confirmationExists(createCandidate(candidateEntity))).isTrue();

    }

    @Test
    public void should_have_no_confirmation_for_a_new_candidate() throws Exception {
        final Candidate newCandidate = Candidate.singleRoomWithEmail("test");
        final boolean isCandidateConfirmed = confirmationRepository.confirmationExists(newCandidate);
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
        confirmationEntity.setConfirmationDate(canfirmationDate);
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
