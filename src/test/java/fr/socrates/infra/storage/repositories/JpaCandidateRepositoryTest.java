package fr.socrates.infra.storage.repositories;

import fr.socrates.SocratesApplication;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.infra.storage.entities.CandidateEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SocratesApplication.class})
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class JpaCandidateRepositoryTest {
    @Autowired
    private JpaCandidateRepository jpaCandidateRepository;

    @Before
    public void setUp() throws Exception {
        jpaCandidateRepository.save(CandidateEntity.fromDomain(Candidate.withEmail("hello@world.fr")));
    }

    @After
    public void tearDown() throws Exception {
        jpaCandidateRepository.deleteAll();
    }

    @Test
    public void should_find_all_candidates() throws Exception {
        assertThat(jpaCandidateRepository.findAll()).contains(new CandidateEntity(1L, "john@doe.fr"));
    }

    @Test
    public void should_find_a_candidate_by_email() throws Exception {
        assertThat(jpaCandidateRepository.findByEmail("hello@world.fr").toDomain()).isEqualTo(Candidate.withEmail("hello@world.fr"));
    }
}
