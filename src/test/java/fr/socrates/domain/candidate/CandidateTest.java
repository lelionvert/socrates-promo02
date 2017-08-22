package fr.socrates.domain.candidate;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CandidateTest {
    @Test(expected = IllegalStateException.class)
    public void should_not_allow_a_candidate_with_an_uninitialized_email() throws Exception {
        Candidate.withEmail(null);
    }

    @Test
    public void two_candidates_instances_with_different_id_are_different() throws Exception {
        Candidate candidate1 = Candidate.withEmail("test@hello.com");
        Candidate candidate2 = Candidate.withEmail("testtutu@hello.com");
        assertThat(candidate1).isNotEqualTo(candidate2);
    }
}
