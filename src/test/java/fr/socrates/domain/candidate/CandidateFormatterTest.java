package fr.socrates.domain.candidate;

import org.assertj.core.api.Assertions;
import org.assertj.core.internal.cglib.core.CollectionUtils;
import org.assertj.core.util.Lists;
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

    @Test
    public void should_keep_candidate_list_order() throws Exception {
        final CandidateFormatter candidateFormatter = new CandidateFormatter();
        final String email = "test@test.com";
        final String email2 = "test2@test.com";

        assertThat(candidateFormatter.format(Lists.newArrayList(Candidate.withEmail(email), Candidate.withEmail(email2))))
                .containsExactly(email, email2);
    }

}
