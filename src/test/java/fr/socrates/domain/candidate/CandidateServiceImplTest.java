package fr.socrates.domain.candidate;

import fr.socrates.infra.repositories.InMemoryCandidateRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
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

    @Test(expected = CandidateExistingException.class)
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
        candidateService.add(Candidate.singleRoomWithEmail(email));

    }


    @Test(expected = CandidateExistingException.class)
    public void should_reject_candidate_when_already_exists() throws Exception {
        final String email = "john@doe.fr";
        candidateService.add(Candidate.singleRoomWithEmail(email));
        candidateService.add(Candidate.singleRoomWithEmail(email));

    }

    @Test(expected = CandidatePersisteDataException.class)
    public void should_manage_candidate_failure_in_persistence() throws Exception {
        final String email = "john@doe.fr";
        final Candidate candidate = Candidate.singleRoomWithEmail(email);
        candidateService = new CandidateServiceImpl(candidateRepository);
        when(candidateRepository.save(any())).thenReturn(false);
        when(candidateRepository.findByEmail(any())).thenReturn(Optional.empty());
        candidateService.add(candidate);


    }


}
