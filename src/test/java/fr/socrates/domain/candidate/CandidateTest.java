package fr.socrates.domain.candidate;

import org.junit.Test;

import static fr.socrates.domain.candidate.Candidate.CandidateBuilder.aCandidate;
import static org.assertj.core.api.Assertions.assertThat;

public class CandidateTest {
    @Test(expected = IllegalStateException.class)
    public void should_not_allow_a_candidate_with_an_uninitialized_email() throws Exception {
        aCandidate().withAccommodationChoices(AccommodationChoice.SINGLE_ROOM).build();
    }

    @Test
    public void two_candidates_instances_with_different_id_are_different() throws Exception {
        Candidate candidate1 = Candidate.singleRoomWithEmail("test@hello.com");
        Candidate candidate2 = Candidate.singleRoomWithEmail("testtutu@hello.com");
        assertThat(candidate1).isNotEqualTo(candidate2);
    }

    @Test(expected = IllegalStateException.class)
    public void should_not_allow_to_create_a_candidate_without_first_accommodation_choice() throws Exception {
        aCandidate().withEmail(EMail.of("alchimiste@lcdlv.fr")).build();
    }
}
