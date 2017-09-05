package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.common.AccommodationChoice;
import fr.socrates.domain.common.AccommodationChoices;
import fr.socrates.infra.storage.entities.CandidateEntity;
import fr.socrates.infra.storage.repositories.CandidateRepositoryAdaptor;
import fr.socrates.infra.storage.repositories.InMemoryCandidateRepository;
import fr.socrates.infra.storage.repositories.JpaCandidateRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static fr.socrates.domain.candidate.ContactInformation.ContactInformationsBuilder.aContactInformations;
import static fr.socrates.domain.common.AccommodationChoices.AccommodationChoicesBuilder;
import static fr.socrates.domain.common.AccommodationChoices.AccommodationChoicesBuilder.anAccommodationChoices;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UpdateCandidateFeature {
    @Mock
    private JpaCandidateRepository jpaCandidateRepository;
    private CandidateService candidateService;

    @Before
    public void setUp() throws Exception {
        candidateService = new CandidateServiceImpl(new CandidateRepositoryAdaptor(jpaCandidateRepository));
    }

    @Test
    public void should_candidate_be_updated() throws Exception {
        final String emailCandidate = "toto@gmail.com";
        Candidate candidate = Candidate.CandidateBuilder.aCandidate()
                .withEmail(EMail.of(emailCandidate))
                .withCandidateId(new CandidateId(emailCandidate))
                .withAccommodationChoices(AccommodationChoicesBuilder.anAccommodationChoices()
                        .withAccommodationChoices(AccommodationChoice.SINGLE_ROOM).build()).build();

        given(jpaCandidateRepository.findByEmail(emailCandidate)).willReturn(null);
        given(jpaCandidateRepository.save(CandidateEntity.fromDomain(candidate))).willReturn(CandidateEntity.fromDomain(candidate));

        candidateService.add(candidate);

        final AccommodationChoices accommodationChoices = anAccommodationChoices()
                .withFirstChoice(AccommodationChoice.DOUBLE_ROOM)
                .withRemarks("j'aime la moto").build();
        final ContactInformation contactInformation = aContactInformations()
                .withTwitter("@Arolla")
                .withPhoneNumber(PhoneNumber.of("0600010203")).build();


        given(jpaCandidateRepository.findByEmail(emailCandidate)).willReturn(CandidateEntity.fromDomain(candidate));

        candidateService.update(EMail.of(emailCandidate), accommodationChoices, contactInformation);
        Optional<Candidate> candidateToCheckPresence = candidateService.findCandidateByEmail(emailCandidate);
        Assertions.assertThat(candidateToCheckPresence).isPresent();
        Assertions.assertThat(candidateToCheckPresence.get().printDetail()).isEqualTo("Candidate{candidateId=CandidateId{id='toto@gmail.com'}, email=toto@gmail.com, accommodationChoices=AccommodationChoices{accommodationChoices=[AccommodationChoice{accommodationChoiceValue='Double Room'}, null, null, null], remarks='j'aime la moto'}, contactInformation=ContactInformation{twitter='@Arolla', phoneNumber=PhoneNumber{phoneNumber='0600010203'}}}");


        verify(jpaCandidateRepository).findByEmail(emailCandidate);
        verify(jpaCandidateRepository).save(CandidateEntity.fromDomain(candidate));
    }
}
