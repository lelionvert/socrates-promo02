package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;
import fr.socrates.infra.repositories.InMemoryCandidateRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static fr.socrates.domain.candidate.AccommodationChoices.*;
import static fr.socrates.domain.candidate.AccommodationChoices.AccommodationChoicesBuilder.anAccommodationChoices;
import static fr.socrates.domain.candidate.ContactInformations.ContactInformationsBuilder.aContactInformations;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UpdateCandidateFeature {


    @Test
    public void should_candidate_be_updated() throws Exception {

        Candidate candidate = Candidate.CandidateBuilder.aCandidate()
                .withEmail(EMail.of("toto@gmail.com"))
                .withAccommodationChoices(AccommodationChoicesBuilder.anAccommodationChoices()
                        .withAccommodationChoices(AccommodationChoice.SINGLE_ROOM).build()).build();

        CandidateService candidateService = new CandidateServiceImpl(new InMemoryCandidateRepository());


        candidateService.add(candidate);

        final AccommodationChoices accommodationChoices = anAccommodationChoices()
                .withFirstChoice(AccommodationChoice.DOUBLE_ROOM)
                .withRemarks("J'aime la moto").build();
        final ContactInformations contactInformations = aContactInformations()
                .withTwitter("@Arolla")
                .withPhoneNumber(PhoneNumber.of("0600010203")).build();

        candidateService.update(EMail.of("toto@gmail.com"), accommodationChoices, contactInformations);
        Optional<Candidate> candidateToCheckPresence = candidateService.findCandidateByEmail("toto@gmail.com");
        Assertions.assertThat(candidateToCheckPresence).isPresent();
        Assertions.assertThat(candidateToCheckPresence.get().printDetail()).isEqualTo("Candidate { email : toto@gmail.fr , Choices [DoubleRoom] , Remarks : j'aime la moto , Twitter : @Arolla , phone : 06010203 }");
    }


}
