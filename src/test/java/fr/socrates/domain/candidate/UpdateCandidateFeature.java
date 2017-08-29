package fr.socrates.domain.candidate;

import fr.socrates.infra.repositories.InMemoryCandidateRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static fr.socrates.domain.candidate.AccommodationChoices.AccommodationChoicesBuilder;
import static fr.socrates.domain.candidate.AccommodationChoices.AccommodationChoicesBuilder.anAccommodationChoices;
import static fr.socrates.domain.candidate.ContactInformation.ContactInformationsBuilder.aContactInformations;


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
        final ContactInformation contactInformation = aContactInformations()
                .withTwitter("@Arolla")
                .withPhoneNumber(PhoneNumber.of("0600010203")).build();

        candidateService.update(EMail.of("toto@gmail.com"), accommodationChoices, contactInformation);
        Optional<Candidate> candidateToCheckPresence = candidateService.findCandidateByEmail("toto@gmail.com");
        Assertions.assertThat(candidateToCheckPresence).isPresent();
        Assertions.assertThat(candidateToCheckPresence.get().printDetail()).isEqualTo("Candidate{candidateId=null, email=toto@gmail.com, accommodationChoices=AccommodationChoices{accommodationChoices=[AccommodationChoice{accommodationChoiceValue='Double Room'}, null, null, null], remarks='j'aime la moto'}, contactInformation=ContactInformation{twitter='@Arrola', phoneNumber=PhoneNumber{phoneNumber='06010203'}}}");

    }


}
