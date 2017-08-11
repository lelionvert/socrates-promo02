package fr.socrates.domain.candidate;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CandidateServiceTest {

    private CandidateService candidateService;

    @Before
    public void setUp() throws Exception {
        candidateService = new CandidateServiceImpl(new FakeCandidateRepository());
    }

    @Test
    public void should_have_no_candidates_at_the_beginning() {
        assertThat(candidateService.hasCandidates()).isFalse();
    }

    @Test
    public void should_have_candidates_when_adding_candidates() throws Exception {
        candidateService.add(Candidate.withEmail(EMail.of("test@test.net")));

        assertThat(candidateService.hasCandidates()).isTrue();
    }

    @Test
    public void should_have_one_candidate_when_adding_one_candidate() throws Exception {
        candidateService.add(Candidate.withEmail(EMail.of("test@test.net")));
        assertThat(candidateService.size()).isEqualTo(1);
    }

    @Test
    public void should_contains_the_candidate_I_have_added() {
        // Arrange
        final String email = "test@test.net";

        // Act
        candidateService.add(Candidate.withEmail(EMail.of(email)));

        // Assert
        assertThat(candidateService.contains(Candidate.withEmail(EMail.of(email)))).isTrue();
    }

    @Test
    public void should_guaranty_unicity_of_candidates() throws Exception {
        candidateService.add(Candidate.withEmail(EMail.of("test@test.net")));
        candidateService.add(Candidate.withEmail(EMail.of("test@test.net")));
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
