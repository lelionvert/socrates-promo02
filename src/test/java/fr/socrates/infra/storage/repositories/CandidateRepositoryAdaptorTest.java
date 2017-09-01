package fr.socrates.infra.storage.repositories;

import fr.socrates.SocratesApplication;
import fr.socrates.domain.CandidateId;
import fr.socrates.domain.candidate.*;
import fr.socrates.domain.candidate.exceptions.AddCandidateException;
import fr.socrates.domain.candidate.exceptions.UnknownCandidateException;
import fr.socrates.domain.candidate.exceptions.UpdateCandidateNoNewDefinitionException;
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

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SocratesApplication.class})
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class CandidateRepositoryAdaptorTest {
    @Autowired
    private JpaCandidateRepository jpaCandidateRepository;

    private CandidateRepository candidateRepository;

    @Before
    public void setUp() throws Exception {
        candidateRepository = new CandidateRepositoryAdaptor(jpaCandidateRepository);
    }

    @After
    public void tearDown() throws Exception {
        jpaCandidateRepository.deleteAll();
    }

    @Test
    public void should_find_all_candidates_from_jpa_repository() throws Exception {
        jpaCandidateRepository.save(CandidateEntity.fromDomain(Candidate.singleRoomWithEmail("hello@world.fr")));
        assertThat(candidateRepository.findAll()).extracting("email").containsOnly("hello@world.fr");
    }

    @Test(expected = AddCandidateException.class)
    public void should_throw_add_candidate_exception_if_a_candidate_cannot_be_saved() throws Exception {
        candidateRepository.addCandidate(null);
    }

    @Test
    public void should_save_a_candidate_in_repository() throws Exception {
        assertThat(candidateRepository.addCandidate(Candidate.singleRoomWithEmail("john@doe.fr"))).extracting("email").containsOnly("john@doe.fr");
        assertThat(jpaCandidateRepository.findAll()).extracting("email").containsOnly("john@doe.fr");
    }

    @Test(expected = UpdateCandidateNoNewDefinitionException.class)
    public void should_throw_update_candidate_exception_if_new_candidate_definition_is_null() throws Exception {
        candidateRepository.updateCandidate(null, "john@doe.fr");
    }

    @Test(expected = UnknownCandidateException.class)
    public void should_throw_update_candidate_unknown_candidate_exception_if_old_email_is_null() throws Exception {
        candidateRepository.updateCandidate(Candidate.singleRoomWithEmail("john@doe.fr"), null);
    }

    @Test(expected = UnknownCandidateException.class)
    public void should_throw_update_candidate_unknown_candidate_exception_if_old_email_is_empty() throws Exception {
        candidateRepository.updateCandidate(Candidate.singleRoomWithEmail("john@doe.fr"), "");
    }

    @Test(expected = UnknownCandidateException.class)
    public void should_throw_update_candidate_unknown_candidate_exception_if_candidate_is_not_found() throws Exception {
        candidateRepository.updateCandidate(Candidate.singleRoomWithEmail("john@doe.fr"), "hello@word.fr");
    }

    @Test
    public void should_update_a_candidate_in_repository() throws Exception {
        jpaCandidateRepository.save(CandidateEntity.fromDomain(Candidate.singleRoomWithEmail("john@doe.fr")));
        Candidate updatedCandidate = Candidate.singleRoomWithEmail("johndoe@socrates.fr");
        assertThat(candidateRepository.updateCandidate(updatedCandidate, "john@doe.fr")).extracting("email").containsOnly("johndoe@socrates.fr");
        assertThat(jpaCandidateRepository.findAll()).extracting("email").containsOnly("johndoe@socrates.fr");
    }

    @Test(expected = UnknownCandidateException.class)
    public void should_throw_an_unknown_candidate_exception_when_candidate_not_found_with_its_email() throws Exception {
        candidateRepository.findCandidateByEmail("john@doe.fr");
    }

    @Test
    public void should_return_candidate_when_a_candidate_has_been_found_with_its_email() throws Exception {
        jpaCandidateRepository.save(CandidateEntity.fromDomain(Candidate.singleRoomWithEmail("john@doe.fr")));
        assertThat(candidateRepository.findCandidateByEmail("john@doe.fr")).extracting("email").containsOnly("john@doe.fr");
    }

    @Test(expected = UnknownCandidateException.class)
    public void should_throw_an_unknown_candidate_exception_when_candidate_not_found_with_his_candidate_id() throws Exception {
        candidateRepository.findCandidateById(new CandidateId("john@doe.fr"));
    }

    @Test
    public void should_return_candidate_when_a_candidate_has_been_found_with_its_candidate_id() throws Exception {
        jpaCandidateRepository.save(CandidateEntity.fromDomain(Candidate.singleRoomWithEmail("john@doe.fr")));
        assertThat(candidateRepository.findCandidateById(new CandidateId("john@doe.fr"))).isEqualTo(Candidate.singleRoomWithEmail("john@doe.fr"));
    }
}
