package fr.socrates.infra.storage.repositories;

import fr.socrates.SocratesApplication;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SocratesApplication.class})
@SpringBootTest
public class JdbcCandidateRepositoryTest {
    @Autowired
    private CandidateRepository jdbcCandidateRepository;

    @Test
    public void should_find_all_candidates() throws Exception {
        assertThat(jdbcCandidateRepository.findAll()).contains(Candidate.withEmail("john@doe.fr"));
    }

    @Test
    public void should_find_one_candidate() throws Exception {
        assertThat(jdbcCandidateRepository.findByEmail("john@doe.fr").get()).isEqualTo(Candidate.withEmail("john@doe.fr"));
    }

    @Test
    public void should_add_one_candidate_in_database() throws Exception {
        assertThat(jdbcCandidateRepository.save(Candidate.withEmail("johndoe@dodo.fr"))).isTrue();
        jdbcCandidateRepository.delete(Candidate.withEmail("johndoe@dodo.fr"));
    }

    @Test
    public void should_update_a_candidate_in_database() throws Exception {
        jdbcCandidateRepository.save(Candidate.withEmail("dodo@john.fr"));
        assertThat(jdbcCandidateRepository.update(Candidate.withEmail("johnupdated@doe.fr"), "dodo@john.fr")).isTrue();
        jdbcCandidateRepository.delete(Candidate.withEmail("johnupdated@doe.fr"));
    }

    @Test
    public void should_delete_a_candidate_in_database() throws Exception {
        jdbcCandidateRepository.save(Candidate.withEmail("test@test.fr"));
        assertThat(jdbcCandidateRepository.delete(Candidate.withEmail("test@test.fr"))).isTrue();
        assertThat(jdbcCandidateRepository.findAll()).doesNotContain(Candidate.withEmail("test@test.fr"));
    }
}
