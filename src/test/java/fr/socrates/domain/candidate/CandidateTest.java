package fr.socrates.domain.candidate;

import fr.socrates.domain.common.AccommodationChoice;
import fr.socrates.domain.common.AccommodationChoices;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static fr.socrates.domain.candidate.Candidate.CandidateBuilder.aCandidate;
import static org.assertj.core.api.Assertions.assertThat;

public class CandidateTest {
    @Test(expected = IllegalStateException.class)
    public void should_not_allow_a_candidate_with_an_uninitialized_email() throws Exception {
        aCandidate().withOneAccommodationChoice(AccommodationChoice.SINGLE_ROOM).build();
    }

    @Test
    public void two_candidates_instances_with_different_id_are_different() throws Exception {
        Candidate candidate1 = Candidate.singleRoomWithEmail("test@hello.com");
        Candidate candidate2 = Candidate.singleRoomWithEmail("testtutu@hello.com");
        assertThat(candidate1).isNotEqualTo(candidate2);
    }

    @Test(expected = IllegalStateException.class)
    public void should_not_allow_to_create_a_candidate_without_first_accommodation_choice() throws Exception {
        aCandidate().withEmail(EMail.of("alchimiste@lcdlv.fr")).build();
    }

    @Test
    public void should_print_candidates() throws Exception {

        Candidate candidate = createCandidate("06010203" , "@Arrola","j'aime la moto", "toto@gmail.com", AccommodationChoice.DOUBLE_ROOM);
        Candidate candidate2 = createCandidate("06666622" , "@Dudul","j'aime le velo", "gerard@gmail.com", AccommodationChoice.SINGLE_ROOM);

        Assertions.assertThat(candidate.printDetail()).isEqualTo("Candidate{candidateId=null, email=toto@gmail.com, accommodationChoices=AccommodationChoices{accommodationChoices=[AccommodationChoice{accommodationChoiceValue='Double Room'}, null, null, null], remarks='j'aime la moto'}, contactInformation=ContactInformation{twitter='@Arrola', phoneNumber=PhoneNumber{phoneNumber='06010203'}}}");
        Assertions.assertThat(candidate2.printDetail()).isEqualTo("Candidate{candidateId=null, email=gerard@gmail.com, accommodationChoices=AccommodationChoices{accommodationChoices=[AccommodationChoice{accommodationChoiceValue='Single Room'}, null, null, null], remarks='j'aime le velo'}, contactInformation=ContactInformation{twitter='@Dudul', phoneNumber=PhoneNumber{phoneNumber='06666622'}}}");
    }

    private Candidate createCandidate(String phoneNumber, String twitter, String remarks, String email, AccommodationChoice typeRoom) {
        ContactInformation contactInformation = ContactInformation.ContactInformationsBuilder.aContactInformations()
                .withPhoneNumber(PhoneNumber.of(phoneNumber))
                .withTwitter(twitter)
                .build();

        AccommodationChoices accommodationChoices = AccommodationChoices.AccommodationChoicesBuilder.anAccommodationChoices()
                .withFirstChoice(typeRoom)
                .withRemarks(remarks)
                .build();

        return Candidate.CandidateBuilder.aCandidate()
                .withEmail(EMail.of(email))
                .withContactInformations(contactInformation)
                .withAccommodationChoices(accommodationChoices)
                .build();
    }
}
