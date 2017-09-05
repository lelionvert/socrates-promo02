package fr.socrates.domain.candidate;

import fr.socrates.domain.candidate.exceptions.AddCandidateException;
import fr.socrates.domain.candidate.exceptions.UnknownCandidateException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CandidateServiceImplTest {
    private CandidateService candidateService;
    @Mock
    CandidateRepository candidateRepository;

    @Before
    public void setUp() throws Exception {
        candidateService = new CandidateServiceImpl(candidateRepository);
    }

    @Test
    public void should_have_no_candidates_at_the_beginning() {
        assertThat(candidateService.getRegisteredCandidates()).isEmpty();
    }

    @Test
    public void should_have_candidate_when_adding_candidate() throws Exception {
        final String email = "test@test.net";
        final Candidate candidate = Candidate.singleRoomWithEmail(email);
        given(candidateRepository.findCandidateByEmail(email)).willReturn(null);
        given(candidateRepository.addCandidate(candidate)).willReturn(candidate);

        candidateService.add(candidate);
        verify(candidateRepository,never()).addCandidate(candidate);
    }


    @Test
    public void should_find_a_candidate_by_email() throws Exception {
        final String email = "john@doe.fr";
        final Candidate candidate = Candidate.singleRoomWithEmail(email);
        given(candidateRepository.findCandidateByEmail(email)).willReturn(candidate);
        assertThat(candidateService.findCandidateByEmail(email).isPresent()).isTrue();
    }

    @Test
    public void should_accept_to_add_candidate_if_he_doesnt_exist_before() throws Exception {
        final String email = "john@doe.fr";
        final Candidate candidate = Candidate.singleRoomWithEmail(email);
        given(candidateRepository.findCandidateByEmail(email)).willReturn(candidate);
        candidateService.add(Candidate.singleRoomWithEmail(email));

    }

    @Test(expected = AddCandidateException.class)
    public void should_manage_candidate_failure_in_persistence() throws Exception {
        final String email = "john@doe.fr";
        final Candidate candidate = Candidate.singleRoomWithEmail(email);
        candidateService = new CandidateServiceImpl(candidateRepository);
        when(candidateRepository.findCandidateByEmail(any())).thenThrow(new UnknownCandidateException());
        when(candidateRepository.addCandidate(any())).thenThrow(new AddCandidateException());
        candidateService.add(candidate);


    }


}
