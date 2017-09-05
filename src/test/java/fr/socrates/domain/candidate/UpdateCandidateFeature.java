package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.candidate.Candidate.CandidateBuilder;
import fr.socrates.domain.common.AccommodationChoice;
import fr.socrates.domain.common.AccommodationChoices;
import fr.socrates.infra.repositories.InMemoryCandidateRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static fr.socrates.domain.candidate.ContactInformation.ContactInformationsBuilder.aContactInformations;
import static fr.socrates.domain.common.AccommodationChoices.AccommodationChoicesBuilder;
import static fr.socrates.domain.common.AccommodationChoices.AccommodationChoicesBuilder.anAccommodationChoices;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class UpdateCandidateFeature {
    private static final String TOTO_EMAIL = "toto@gmail.com";
    private CandidateService candidateService;

    @Before
    public void setUp() throws Exception {

        Candidate candidate = CandidateBuilder.aCandidate()
                .withEmail(EMail.of(TOTO_EMAIL))
                .withCandidateId(new CandidateId(TOTO_EMAIL))
                .withAccommodationChoices(AccommodationChoicesBuilder.anAccommodationChoices()
                        .withAccommodationChoices(AccommodationChoice.SINGLE_ROOM).build()).build();

        candidateService = new CandidateServiceImpl(new InMemoryCandidateRepository());
        candidateService.add(candidate);
    }

    @Test
    public void should_candidate_be_updated() throws Exception {
        final AccommodationChoices accommodationChoices = anAccommodationChoices()
                .withAccommodationChoices(AccommodationChoice.DOUBLE_ROOM)
                .withRemarks("j'aime la moto").build();
        final ContactInformation contactInformation = aContactInformations()
                .withTwitter("@Arolla")
                .withPhoneNumber(PhoneNumber.of("0600010203")).build();

        candidateService.update(EMail.of(TOTO_EMAIL), accommodationChoices, contactInformation);

        Optional<Candidate> candidateToCheckPresence = candidateService.findCandidateByEmail(TOTO_EMAIL);
        assertThat(candidateToCheckPresence).isPresent();
        candidateToCheckPresence.ifPresent(candidate -> {
            final Candidate expectedCandidate = CandidateBuilder.aCandidate()
                    .withCandidateId(new CandidateId(TOTO_EMAIL))
                    .withEmail(EMail.of(TOTO_EMAIL))
                    .withAccommodationChoices(accommodationChoices)
                    .withContactInformations(contactInformation)
                    .build();
            assertThat(candidate).isEqualTo(expectedCandidate);
        });
    }


}
