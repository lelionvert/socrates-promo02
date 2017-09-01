package fr.socrates.domain.attendee;

import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateService;
import fr.socrates.domain.candidate.CandidateServiceImpl;
import fr.socrates.domain.common.AccommodationChoice;

import fr.socrates.infra.storage.repositories.InMemoryCandidateRepository;
import fr.socrates.infra.storage.repositories.InMemoryConfirmationRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class ConfirmationFeature {

    private ConfirmationService confirmationService;
    private CandidateService candidateService;

    @Before
    public void setUp() throws Exception {
        final InMemoryCandidateRepository candidateRepository = new InMemoryCandidateRepository();
        confirmationService = new ConfirmationServiceImpl(candidateRepository, new InMemoryConfirmationRepository());
        candidateService = new CandidateServiceImpl(candidateRepository);
    }

    @Test
    public void should_save_accommodation_after_confirmation() throws Exception {
        final LocalDate now = LocalDate.now();
        Candidate john = Candidate.singleRoomWithEmail("john@test.com");
        Candidate patrick = Candidate.singleRoomWithEmail("patrick@test.com");
        Candidate peter = Candidate.singleRoomWithEmail("peter@test.com");

        candidateService.add(john);
        candidateService.add(patrick);
        candidateService.add(peter);

        confirmationService.confirm("john@test.com", now, Payment.TRANSFER, AccommodationChoice.SINGLE_ROOM);
        confirmationService.confirm("patrick@test.com", now, Payment.AT_CHECKOUT, AccommodationChoice.DOUBLE_ROOM);

        List<Confirmation> confirmations = confirmationService.getListConfirmations();

        final Confirmation johnConfirmation = confirmations.get(0);
        Assertions.assertThat(johnConfirmation.toString()).isEqualTo("Confirmation{candidateId=" + john.getCandidateId() +
                ", confirmationDate=" + now +
                ", accommodationChoice=" + AccommodationChoice.SINGLE_ROOM +
                ", payment=" + Payment.TRANSFER +
                '}');

        final Confirmation patrickConfirmation = confirmations.get(1);
        Assertions.assertThat(patrickConfirmation.toString()).isEqualTo("Confirmation{candidateId=" + patrick.getCandidateId() +
                ", confirmationDate=" + now +
                ", accommodationChoice=" + AccommodationChoice.DOUBLE_ROOM +
                ", payment=" + Payment.AT_CHECKOUT +
                '}');
    }
}
