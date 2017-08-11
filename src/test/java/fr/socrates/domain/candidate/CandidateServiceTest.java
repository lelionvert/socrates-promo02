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
        candidateService.add(Candidate.withEmail("test@test.net"));

        assertThat(candidateService.hasCandidates()).isTrue();
    }

    @Test
    public void should_have_one_candidate_when_adding_one_candidate() throws Exception {
        candidateService.add(Candidate.withEmail("test@test.net"));
        assertThat(candidateService.size()).isEqualTo(1);
    }

    @Test
    public void should_contains_the_candidate_I_have_added() {
        // Arrange
        final String email = "test@test.net";

        // Act
        candidateService.add(Candidate.withEmail(email));

        // Assert
        assertThat(candidateService.contains(Candidate.withEmail(email))).isTrue();
    }

    @Test
    public void should_guaranty_unicity_of_candidates() throws Exception {
        candidateService.add(Candidate.withEmail("test@test.net"));
        candidateService.add(Candidate.withEmail("test@test.net"));
        assertThat(candidateService.size()).isEqualTo(1);
    }

    @Test
    public void should_print_no_candidates_at_the_beginning() throws Exception {
        assertThat(candidateService.print()).isEqualTo("No email to print");
    }

    @Test
    public void should_print_one_email_when_adding_one_candidate() throws Exception {
        candidateService.add(Candidate.withEmail("test@test.net"));
        assertThat(candidateService.print()).isEqualTo("test@test.net");
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
