package fr.socrates.infra.storage.repositories;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateRepository;
import fr.socrates.domain.candidate.exceptions.AddCandidateException;
import fr.socrates.domain.candidate.exceptions.UnknownCandidateException;
import fr.socrates.domain.candidate.exceptions.UpdateCandidateNoNewDefinitionException;
import fr.socrates.infra.storage.entities.CandidateEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CandidateRepositoryAdaptorTest {
    @Mock
    JpaCandidateRepository jpaCandidateRepository;

    CandidateRepository candidateRepository;


    @Before
    public void setUp() throws Exception {
        candidateRepository = new CandidateRepositoryAdaptor(jpaCandidateRepository);
    }


    @Test
    public void should_find_all_candidates_from_jpa_repository() throws Exception {
        candidateRepository.findAll();
        verify(jpaCandidateRepository).findAll();

    }

    @Test(expected = AddCandidateException.class)
    public void should_throw_add_candidate_exception_if_a_candidate_cannot_be_saved() throws Exception {
        candidateRepository.addCandidate(null);
    }

    @Test
    public void should_save_a_candidate_in_repository() throws Exception {
        final Candidate candidate = Candidate.singleRoomWithEmail("john@doe.fr");
        final CandidateEntity candidateEntity = CandidateEntity.fromDomain(candidate);
        given(jpaCandidateRepository.save(candidateEntity)).willReturn(candidateEntity);
        candidateRepository.addCandidate(candidate);
        verify(jpaCandidateRepository).save(candidateEntity);
    }

    @Test(expected = UpdateCandidateNoNewDefinitionException.class)
    public void should_throw_update_candidate_exception_if_new_candidate_definition_is_null() throws Exception {
        candidateRepository.updateCandidate(null, "john@doe.fr");
    }

    @Test(expected = UnknownCandidateException.class)
    public void should_throw_update_candidate_unknown_candidate_exception_if_old_email_is_null() throws Exception {
        candidateRepository.updateCandidate(Candidate.singleRoomWithEmail("john@doe.fr"), null);
    }

    @Test(expected = UnknownCandidateException.class)
    public void should_throw_update_candidate_unknown_candidate_exception_if_old_email_is_empty() throws Exception {
        candidateRepository.updateCandidate(Candidate.singleRoomWithEmail("john@doe.fr"), "");
    }

    @Test(expected = UnknownCandidateException.class)
    public void should_throw_update_candidate_unknown_candidate_exception_if_candidate_is_not_found() throws Exception {
        candidateRepository.updateCandidate(Candidate.singleRoomWithEmail("john@doe.fr"), "hello@word.fr");
    }

    @Test
    public void should_update_a_candidate_in_repository() throws Exception {
        final String email = "john@doe.fr";
        final Candidate candidate = Candidate.singleRoomWithEmail(email);

        final CandidateEntity candidateEntity = CandidateEntity.fromDomain(candidate);
        final Candidate updatedCandidate = Candidate.singleRoomWithEmail("johndoe@socrates.fr");

        given(jpaCandidateRepository.findByEmail(email)).willReturn(candidateEntity);
        given(jpaCandidateRepository.save(candidateEntity)).willReturn(CandidateEntity.fromDomain(updatedCandidate));

        candidateRepository.updateCandidate(updatedCandidate, email);

        verify(jpaCandidateRepository).save(candidateEntity);
    }

    @Test(expected = UnknownCandidateException.class)
    public void should_throw_an_unknown_candidate_exception_when_candidate_not_found_with_its_email() throws Exception {
        candidateRepository.findCandidateByEmail("john@doe.fr");
    }

    @Test
    public void should_return_candidate_when_a_candidate_has_been_found_with_its_email() throws Exception {

        final String email = "john@doe.fr";
        final CandidateEntity candidate = CandidateEntity.fromDomain(Candidate.singleRoomWithEmail(email));
        given(jpaCandidateRepository.findByEmail(email)).willReturn(candidate);
        candidateRepository.findCandidateByEmail(email);
        verify(jpaCandidateRepository).findByEmail(email);

    }

    @Test(expected = UnknownCandidateException.class)
    public void should_throw_an_unknown_candidate_exception_when_candidate_not_found_with_his_candidate_id() throws Exception {
        candidateRepository.findCandidateById(new CandidateId("john@doe.fr"));
    }

    @Test
    public void should_return_candidate_when_a_candidate_has_been_found_with_its_candidate_id() throws Exception {
        final String candidateID = "john@doe.fr";
        final CandidateEntity candidate = CandidateEntity.fromDomain(Candidate.singleRoomWithEmail("john@doe.fr"));
        given(jpaCandidateRepository.findByCandidateId(candidateID)).willReturn(candidate);
        candidateRepository.findCandidateById(new CandidateId(candidateID));
        verify(jpaCandidateRepository).findByCandidateId(candidateID); }
}
