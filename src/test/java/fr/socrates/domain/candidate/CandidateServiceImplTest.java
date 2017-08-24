package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;
import fr.socrates.infra.repositories.InMemoryCandidateRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static fr.socrates.domain.candidate.AccommodationChoices.AccommodationChoicesBuilder.anAccommodationChoices;
import static fr.socrates.domain.candidate.ContactInformations.ContactInformationsBuilder.aContactInformations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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


}
