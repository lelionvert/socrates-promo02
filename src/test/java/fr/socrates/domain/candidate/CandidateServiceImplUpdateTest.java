package fr.socrates.domain.candidate;

import fr.socrates.domain.CandidateId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static fr.socrates.domain.candidate.AccommodationChoices.AccommodationChoicesBuilder.anAccommodationChoices;
import static fr.socrates.domain.candidate.ContactInformation.ContactInformationsBuilder.aContactInformations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CandidateServiceImplUpdateTest {

    private CandidateRepository candidateRepository;

    @Before
    public void setUp() throws Exception {
        Candidate candidate = Candidate.singleRoomWithEmail("toto@gmail.com");
        candidateRepository = Mockito.mock(CandidateRepository.class);
        when(candidateRepository.findByEmail("toto@gmail.com")).thenReturn(Optional.of(candidate));
        candidateService = new CandidateServiceImpl(candidateRepository);

    }

    private CandidateService candidateService;

    @Test
    public void should_update_accommodation_choice_existing_candidate() throws Exception {


        final AccommodationChoices accommodationChoices = anAccommodationChoices()
                .withFirstChoice(AccommodationChoice.DOUBLE_ROOM)
                .withRemarks("J'aime la moto").build();


        candidateService.update(EMail.of("toto@gmail.com"), accommodationChoices, null);
        verify(candidateRepository).updateAccommodationChoices(new CandidateId("toto@gmail.com"), accommodationChoices);


    }

    @Test
    public void should_update_contact_infos_existing_candidate() throws Exception {

        final ContactInformation contactInformation = aContactInformations()
                .withTwitter("@Arolla")
                .withPhoneNumber(PhoneNumber.of("0600010203")).build();


        candidateService.update(EMail.of("toto@gmail.com"), null, contactInformation);
        verify(candidateRepository).findByEmail("toto@gmail.com");
        verify(candidateRepository).updateContactInfos(new CandidateId("toto@gmail.com"), contactInformation);


    }

}