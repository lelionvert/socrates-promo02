package fr.socrates.domain.candidate;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class candidateListTest {
    @Test
    public void should_return_an_empty_list() {
        CandidateList candidateList = new CandidateList();
        assertThat(candidateList.size()).isEqualTo(0);

    }
}
