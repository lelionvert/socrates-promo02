package fr.socrates.infra.storage.repositories;

import fr.socrates.SocratesApplication;
import fr.socrates.domain.CandidateId;
import fr.socrates.domain.attendee.Confirmation;
import fr.socrates.domain.attendee.ConfirmationRepository;
import fr.socrates.infra.storage.entities.CandidateEntity;
import fr.socrates.infra.storage.entities.ConfirmationEntity;
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
        candidate = createCandidate();
        confirmationLocalDate = LocalDateTime.of(2017, 8, 31, 14, 0, 0);
        canfirmationDate = Date.from(confirmationLocalDate.atZone(ZoneId.systemDefault()).toInstant());


    }

    private CandidateEntity createCandidate() {
        CandidateEntity candidate = new CandidateEntity();
        candidate.setEmail("testa@doe.fr");
        candidate.setCandidateId("testa@doe.fr");
        jpaCandidateRepository.save(candidate);
        return candidate;
    }

    @Test
    public void should_add_a_confirmation_of_a_candidate() throws Exception {
        Confirmation candidateConfirmation = new Confirmation(new CandidateId("testa@doe.fr"), confirmationLocalDate);
        confirmationRepository.add(candidateConfirmation);
        ConfirmationEntity confirmationEntity = new ConfirmationEntity();
        confirmationEntity.setCandidate(candidate);
        confirmationEntity.setConfirmationDate(canfirmationDate);
        List<ConfirmationEntity> all = jpaConfirmationRepository.findAll();
        assertThat(all).containsExactly(confirmationEntity);
    }


}