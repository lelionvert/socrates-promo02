package fr.socrates.domain.candidate;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CandidateTest {
    @Test(expected = IllegalStateException.class)
    public void should_not_allow_a_candidate_with_an_uninitialized_email() throws Exception {
        Candidate.withEmail(null);
    }

    @Test
    public void should_create_a_valid_candidate() throws Exception {
        Candidate candidate = Candidate.withEmail(EMail.of("test@hello.com"));
        assertThat(candidate).isNotNull();
    }
}
