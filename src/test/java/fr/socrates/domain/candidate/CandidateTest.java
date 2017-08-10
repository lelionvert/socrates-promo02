package fr.socrates.domain.candidate;

import org.junit.Test;

public class CandidateTest {
    @Test(expected = IllegalStateException.class)
    public void should_not_allow_a_candidate_with_an_uninitialized_email() throws Exception {
        Candidate.withEmail(null);
    }
}
