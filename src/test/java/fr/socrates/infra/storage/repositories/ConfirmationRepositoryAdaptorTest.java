package fr.socrates.infra.storage.repositories;

import fr.socrates.SocratesApplication;
import fr.socrates.domain.CandidateId;
import fr.socrates.domain.attendee.Confirmation;
import fr.socrates.domain.attendee.ConfirmationRepository;
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
    private LocalDateTime confirmationLocalDate;
    private CandidateEntity candidate;

    @Before
    public void setUp() throws Exception {

        confirmationRepository = new ConfirmationRepositoryAdaptor(jpaConfirmationRepository, jpaCandidateRepository);
        confirmationLocalDate = LocalDateTime.of(2017, 8, 31, 14, 0, 0);
        canfirmationDate = Date.from(confirmationLocalDate.atZone(ZoneId.systemDefault()).toInstant());

    }

    @After
    public void tearDown() throws Exception {
        jpaConfirmationRepository.deleteAll();
        jpaCandidateRepository.deleteAll();
    }

    @Test
    public void should_add_a_confirmation_of_a_candidate() throws Exception {
        candidate = createCandidate(1);
        createConfirmation(candidate);
        ConfirmationEntity confirmationEntity = getConfirmationEntity();
        List<ConfirmationEntity> all = jpaConfirmationRepository.findAll();
        assertThat(all).containsExactly(confirmationEntity);
    }

    @Test
    public void should_list_all_confirmations_of_candidates() throws Exception {
        candidate = createCandidate(2);
        final Confirmation confirmation = createConfirmation(candidate);
        assertThat(confirmationRepository.getConfirmations()).containsExactly(confirmation);

    }

    private Confirmation createConfirmation(CandidateEntity candidate) {
        Confirmation candidateConfirmation = new Confirmation(new CandidateId(candidate.getCandidateId()), confirmationLocalDate);
        confirmationRepository.add(candidateConfirmation);
        return candidateConfirmation;
    }

    private ConfirmationEntity getConfirmationEntity() {
        ConfirmationEntity confirmationEntity = new ConfirmationEntity();
        confirmationEntity.setCandidate(candidate);
        confirmationEntity.setConfirmationDate(canfirmationDate);
        return confirmationEntity;
    }

    private CandidateEntity createCandidate(int unicityNumber) {
        CandidateEntity candidate = new CandidateEntity();
        candidate.setEmail(unicityNumber + "mail@server.fr");
        candidate.setCandidateId(unicityNumber + "mail@server.fr");
        jpaCandidateRepository.save(candidate);
        return candidate;
    }
}
