package fr.socrates.domain.candidate;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

public class CandidateFormatterTest {


    @Test
    public void should_return_no_email_to_print_when_the_list_is_empty() throws Exception {
        final CandidateFormatter candidateFormatter = new CandidateFormatter();
        assertThat(candidateFormatter.format(Collections.EMPTY_LIST)).containsExactly("No email to print");
    }

    @Test
    public void should_return_one_email_to_print_when_the_list_contains_one_candidate() throws Exception {
        final CandidateFormatter candidateFormatter = new CandidateFormatter();
        final String email = "test@test.com";
        assertThat(candidateFormatter.format(Collections.singletonList(Candidate.withEmail(email))))
                .containsExactly(email);
    }

}
