package fr.socrates.domain.candidate;

import fr.socrates.infra.repositories.InMemoryCandidateRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

public class CandidateServiceImplTest {
    private CandidateService candidateService;
    @Mock
    CandidateRepository candidateRepository;

    @Before
    public void setUp() throws Exception {
        candidateService = new CandidateServiceImpl(new InMemoryCandidateRepository());
    }

    @Test
    public void should_have_no_candidates_at_the_beginning() {
        assertThat(candidateService.getRegisteredCandidates()).isEmpty();
    }

    @Test
    public void should_have_candidate_when_adding_candidate() throws Exception {
        final String email = "test@test.net";
        candidateService.add(Candidate.singleRoomWithEmail(email));
        assertThat(candidateService.getRegisteredCandidates())
                .containsExactly(Candidate.singleRoomWithEmail(email));
    }

    @Test
    public void should_guaranty_unicity_of_candidates() throws Exception {
        final String email = "test@test.net";
        candidateService.add(Candidate.singleRoomWithEmail(email));
        candidateService.add(Candidate.singleRoomWithEmail(email));
        assertThat(candidateService.getRegisteredCandidates())
                .containsExactly(Candidate.singleRoomWithEmail(email));
    }

    @Test
    public void should_find_a_candidate_by_email() throws Exception {
        final String email = "john@doe.fr";
        candidateService.add(Candidate.singleRoomWithEmail(email));
        assertThat(candidateService.findCandidateByEmail(email).isPresent()).isTrue();
    }

    @Test
    public void should_accept_to_add_candidate_if_he_doesnt_exist_before() throws Exception {
        final String email = "john@doe.fr";
        final boolean candidateAddingStatus = candidateService.add(Candidate.singleRoomWithEmail(email));
        assertThat(candidateAddingStatus).isTrue();
    }

    @Test
    public void should_not_accept_to_add_candidate_if_he_is_already_exist() throws Exception {
        final String email = "john@doe.fr";
        final boolean candidateFirstAddingStatus = candidateService.add(Candidate.singleRoomWithEmail(email));
        final boolean candidateSecondAddingStatus = candidateService.add(Candidate.singleRoomWithEmail(email));
        assertThat(candidateFirstAddingStatus).isTrue();
        assertThat(candidateSecondAddingStatus).isFalse();
    }

}
