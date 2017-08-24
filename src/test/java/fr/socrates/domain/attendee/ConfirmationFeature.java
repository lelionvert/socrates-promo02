package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateService;
import fr.socrates.domain.candidate.CandidateServiceImpl;
import fr.socrates.infra.repositories.InMemoryCandidateRepository;
import fr.socrates.infra.repositories.InMemoryConfirmationRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ConfirmationFeature {

    private ConfirmationService confirmationService;
    private CandidateService candidateService;

    @Before
    public void setUp() throws Exception {
        confirmationService = new ConfirmationServiceImpl(new InMemoryCandidateRepository(), new InMemoryConfirmationRepository());
        candidateService = new CandidateServiceImpl(new InMemoryCandidateRepository());
    }

    @Test
    public void should_save_accommodation_after_confirmation() throws Exception {
        Candidate john = Candidate.withEmail("john@test.com");
        Candidate patrick = Candidate.withEmail("patrick@test.com");
        Candidate peter = Candidate.withEmail("peter@test.com");

        candidateService.add(john);
        candidateService.add(patrick);
        candidateService.add(peter);


        confirmationService.confirm("john@test.com", Accommodation.SINGLE_ROOM, Payment.TRANSFER);
        confirmationService.confirm("patrick@test.com", Accommodation.DOUBLE_ROOM, Payment.AT_CHECKOUT);


        //String getallconfirmedcandidate()
        //tostring de getallconfirmation (renvoie liste de strings ou liste de confirmation)

        List<Confirmation> confirmations = confirmationService.getListConfirmations();

        final Confirmation johnConfirmation = confirmations.get(0);
        Assertions.assertThat(johnConfirmation.getAccommodation()).isEqualTo(Accommodation.SINGLE_ROOM);
        Assertions.assertThat(johnConfirmation.getPayment()).isEqualTo(Payment.TRANSFER);

        final Confirmation patrickConfirmation = confirmations.get(1);
        Assertions.assertThat(patrickConfirmation.getAccommodation()).isEqualTo(Accommodation.DOUBLE_ROOM);
        Assertions.assertThat(patrickConfirmation.getPayment()).isEqualTo(Payment.AT_CHECKOUT);

        //List<String> confirmationsString = confirmations.stream().map(Confirmation::toString).collect(Collectors.toList());

        //assertThat(confirmationService.getConfirmationByCandidate(john).getAccommodation()).isEqualTO(Accommodation.SINGLE_ROOM);
        //assertThat(confirmationService.getConfirmationByCandidate(john).getPayment()).isEqualTO(Payment.TRANSFER);
    }
}
