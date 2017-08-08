package fr.socrates.domain.candidate;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class candidateListTest {
    @Test
    public void should_return_an_empty_list() {
        CandidateService candidateService = new CandidateService(new FakeCandidateConnector());
        assertThat(candidateService.getList()).isEmpty();
    }

    @Test
    public void should_return_one_candidate(){
        CandidateService candidateListOneElement = new CandidateService(new FakeCandidateConnector());
        final String email = "test@test.net";
        candidateListOneElement.add(new Candidate(email));
        assertThat(candidateListOneElement.getList().get(0).getEmail()).isEqualTo(email);
    }

    private class FakeCandidateConnector implements CandidateConnector {
        private final List<Candidate> list = new ArrayList<>();

        @Override
        public List<Candidate> getList() {
            return list;
        }

        @Override
        public void add(Candidate candidate) {
            list.add(candidate);
        }
    }
}
