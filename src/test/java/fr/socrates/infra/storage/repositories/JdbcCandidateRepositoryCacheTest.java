package fr.socrates.infra.storage.repositories;

import fr.socrates.SocratesApplication;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SocratesApplication.class})
@SpringBootTest
public class JdbcCandidateRepositoryCacheTest {
    @Autowired
    private CandidateRepository jdbcCandidateRepository;

    @Before
    public void setUp() throws Exception {
        jdbcCandidateRepository.save(Candidate.withEmail("test@doe.fr"));
        jdbcCandidateRepository.save(Candidate.withEmail("toto@doe.fr"));
    }

    @After
    public void tearDown() throws Exception {
        jdbcCandidateRepository.delete(Candidate.withEmail("test@doe.fr"));
        jdbcCandidateRepository.delete(Candidate.withEmail("toto@doe.fr"));
    }

    @Test
    public void should_cache_candidate_when_fetching_one() throws Exception {
        System.out.println("... Fetching candidate");
        System.out.println("test@doe.fr --> " + jdbcCandidateRepository.findByEmail("test@doe.fr").toString());
        System.out.println("toto@doe.fr --> " + jdbcCandidateRepository.findByEmail("toto@doe.fr").toString());
        System.out.println("test@doe.fr --> " + jdbcCandidateRepository.findByEmail("test@doe.fr").toString());
        System.out.println("toto@doe.fr --> " + jdbcCandidateRepository.findByEmail("toto@doe.fr").toString());
        System.out.println("test@doe.fr --> " + jdbcCandidateRepository.findByEmail("test@doe.fr").toString());
        System.out.println("test@doe.fr --> " + jdbcCandidateRepository.findByEmail("test@doe.fr").toString());
    }
}
