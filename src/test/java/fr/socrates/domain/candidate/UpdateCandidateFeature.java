package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.common.AccommodationChoice;
import fr.socrates.domain.common.AccommodationChoices;
import fr.socrates.infra.storage.repositories.InMemoryCandidateRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static fr.socrates.domain.candidate.ContactInformation.ContactInformationsBuilder.aContactInformations;
import static fr.socrates.domain.common.AccommodationChoices.AccommodationChoicesBuilder;
import static fr.socrates.domain.common.AccommodationChoices.AccommodationChoicesBuilder.anAccommodationChoices;


@RunWith(MockitoJUnitRunner.class)
public class UpdateCandidateFeature {
    @Test
    public void should_candidate_be_updated() throws Exception {
        final String emailCandidate = "toto@gmail.com";

        Candidate candidate = Candidate.CandidateBuilder.aCandidate()
                .withEmail(EMail.of(emailCandidate))
                .withCandidateId(new CandidateId(emailCandidate))
                .withAccommodationChoices(AccommodationChoicesBuilder.anAccommodationChoices()
                        .withAccommodationChoices(AccommodationChoice.SINGLE_ROOM).build()).build();

        CandidateService candidateService = new CandidateServiceImpl(new InMemoryCandidateRepository());


        candidateService.add(candidate);

        final AccommodationChoices accommodationChoices = anAccommodationChoices()
                .withFirstChoice(AccommodationChoice.DOUBLE_ROOM)
                .withRemarks("j'aime la moto").build();
        final ContactInformation contactInformation = aContactInformations()
                .withTwitter("@Arolla")
                .withPhoneNumber(PhoneNumber.of("0600010203")).build();

        candidateService.update(EMail.of(emailCandidate), accommodationChoices, contactInformation);
        Optional<Candidate> candidateToCheckPresence = candidateService.findCandidateByEmail(emailCandidate);
        Assertions.assertThat(candidateToCheckPresence).isPresent();
        Assertions.assertThat(candidateToCheckPresence.get().printDetail()).isEqualTo("Candidate{candidateId=CandidateId{id='toto@gmail.com'}, email=toto@gmail.com, accommodationChoices=AccommodationChoices{accommodationChoices=[AccommodationChoice{accommodationChoiceValue='Double Room'}, null, null, null], remarks='j'aime la moto'}, contactInformation=ContactInformation{twitter='@Arolla', phoneNumber=PhoneNumber{phoneNumber='0600010203'}}}");

    }


}
