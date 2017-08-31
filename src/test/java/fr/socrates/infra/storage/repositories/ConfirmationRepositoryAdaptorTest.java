package fr.socrates.infra.storage.repositories;

import fr.socrates.SocratesApplication;
import fr.socrates.domain.CandidateId;
import fr.socrates.domain.attendee.Confirmation;
import fr.socrates.domain.attendee.ConfirmationRepository;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.infra.storage.entities.CandidateEntity;
import fr.socrates.infra.storage.entities.ConfirmationEntity;
import org.assertj.core.api.Assertions;
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

    @Before
    public void setUp() throws Exception {
        jpaConfirmationRepository.deleteAll();
        jpaCandidateRepository.deleteAll();
        confirmationRepository = new ConfirmationRepositoryAdaptor(jpaConfirmationRepository, jpaCandidateRepository);
    }

    @Test
    public void should_add_a_confirmation_of_a_candidate() throws Exception {
        LocalDateTime confirmationDate = LocalDateTime.of(2017, 8, 31, 14, 0, 0);
        Date date = Date.from(confirmationDate.atZone(ZoneId.systemDefault()).toInstant());

        CandidateEntity candidate = new CandidateEntity();
        candidate.setEmail("test@doe.fr");
        candidate.setCandidateId("test@doe.fr");
        jpaCandidateRepository.save(candidate);

        Confirmation candidateConfirmation = new Confirmation(new CandidateId("test@doe.fr"), confirmationDate);
        confirmationRepository.add(candidateConfirmation);

        System.out.println(jpaCandidateRepository.findAll());

        ConfirmationEntity confirmationEntity = new ConfirmationEntity();
        confirmationEntity.setCandidate(candidate);
        confirmationEntity.setConfirmationDate(date);
        List<ConfirmationEntity> all = jpaConfirmationRepository.findAll();
        System.out.println(all);
        assertThat(all).containsExactly(confirmationEntity);
    }
}