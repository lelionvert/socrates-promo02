package fr.socrates.domain.candidate;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class candidateListTest {

    private CandidateService candidateService;

    @Before
    public void setUp() throws Exception {
        candidateService = new CandidateService(new FakeCandidateRepository());
    }

    @Test
    public void should_return_an_empty_list() {
        assertThat(candidateService.getCandidates()).isEmpty();
    }

    @Test
    public void should_return_one_candidate() {
        // Arrange
        final String email = "test@test.net";

        // Act
        candidateService.add(new Candidate(email));

        // Assert
        Set<Candidate> expectedCandidates = Collections.singleton(new Candidate(email));
        assertThat(candidateService.getCandidates()).isEqualTo(expectedCandidates);
    }

    @Test
    public void should_guaranty_unicity_of_candidates() throws Exception {
        candidateService.add(new Candidate("test@test.net"));
        candidateService.add(new Candidate("test@test.net"));
        assertThat(candidateService.size()).isEqualTo(1);
    }

    private class FakeCandidateRepository implements CandidateRepository {

        private final Set<Candidate> list = new HashSet<>();

        @Override
        public Set<Candidate> findAll() {
            return list;
        }

        @Override
        public void save(Candidate candidate) {
            list.add(candidate);
        }

        @Override
        public int size() {
            return list.size();
        }
    }
}
