package fr.socrates.domain.meal;

import fr.socrates.common.Printer;
import fr.socrates.domain.CandidateId;
import fr.socrates.domain.attendee.ConfirmationService;
import fr.socrates.domain.attendee.ConfirmationServiceImpl;
import fr.socrates.domain.attendee.Payment;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.Candidate.CandidateBuilder;
import fr.socrates.domain.candidate.CandidateService;
import fr.socrates.domain.candidate.CandidateServiceImpl;
import fr.socrates.domain.candidate.EMail;
import fr.socrates.domain.checkin.CheckIn;
import fr.socrates.domain.checkin.CheckInService;
import fr.socrates.domain.checkin.CheckInServiceImpl;
import fr.socrates.domain.common.AccommodationChoice;
import fr.socrates.infra.repositories.InMemoryCandidateRepository;
import fr.socrates.infra.repositories.InMemoryCheckInRepository;
import fr.socrates.infra.repositories.InMemoryConfirmationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static fr.socrates.domain.meal.Diet.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MealCateringFeature {
    private static final String JOHN_DOE_FR = "john@doe.fr";
    private static final String ARTHUR_LEROI_FR = "arthur@leroi.fr";
    private static final String RAJESH_KOOTRAPOLI_COM = "rajesh@kootrapoli.com";
    private static final String SHELDON_COOPER_COM = "sheldon@cooper.com";
    private static final String LEONARD_HOFSTADTER_COM = "leonard@hofstadter.com";
    private static final String HOWARD_WOLOWITZ_COM = "howard@wolowitz.com";
    private static final String LEO_DAGAN_FR = "leo@dagan.fr";
    private static final String KARA_DOC_FR = "kara@doc.fr";
    private static final String PERSE_VAL_FR = "perse@val.fr";
    private Candidate johndoe;
    private Candidate arthurleroi;
    private Candidate rajeshkootrapoli;
    private Candidate sheldoncooper;
    private Candidate leonardhofstadter;
    private Candidate howardwolowitz;
    private Candidate leodagan;
    private Candidate karadoc;
    private Candidate perceval;
    private ConfirmationService confirmationService;
    private MealService mealService;

    @Before
    public void setUp() throws Exception {
        johndoe = CandidateBuilder.aCandidate().withCandidateId(new CandidateId("1")).withEmail(EMail.of(JOHN_DOE_FR)).withOneAccommodationChoice(AccommodationChoice.SINGLE_ROOM).build();
        arthurleroi = CandidateBuilder.aCandidate().withCandidateId(new CandidateId("2")).withEmail(EMail.of(ARTHUR_LEROI_FR)).withOneAccommodationChoice(AccommodationChoice.SINGLE_ROOM).build();
        rajeshkootrapoli = CandidateBuilder.aCandidate().withCandidateId(new CandidateId("3")).withEmail(EMail.of(RAJESH_KOOTRAPOLI_COM)).withOneAccommodationChoice(AccommodationChoice.SINGLE_ROOM).build();
        sheldoncooper = CandidateBuilder.aCandidate().withCandidateId(new CandidateId("4")).withEmail(EMail.of(SHELDON_COOPER_COM)).withOneAccommodationChoice(AccommodationChoice.SINGLE_ROOM).build();
        leonardhofstadter = CandidateBuilder.aCandidate().withCandidateId(new CandidateId("5")).withEmail(EMail.of(LEONARD_HOFSTADTER_COM)).withOneAccommodationChoice(AccommodationChoice.SINGLE_ROOM).build();
        howardwolowitz = CandidateBuilder.aCandidate().withCandidateId(new CandidateId("6")).withEmail(EMail.of(HOWARD_WOLOWITZ_COM)).withOneAccommodationChoice(AccommodationChoice.SINGLE_ROOM).build();
        leodagan = CandidateBuilder.aCandidate().withCandidateId(new CandidateId("7")).withEmail(EMail.of(LEO_DAGAN_FR)).withOneAccommodationChoice(AccommodationChoice.SINGLE_ROOM).build();
        karadoc = CandidateBuilder.aCandidate().withCandidateId(new CandidateId("8")).withEmail(EMail.of(KARA_DOC_FR)).withOneAccommodationChoice(AccommodationChoice.SINGLE_ROOM).build();
        perceval = CandidateBuilder.aCandidate().withCandidateId(new CandidateId("9")).withEmail(EMail.of(PERSE_VAL_FR)).withOneAccommodationChoice(AccommodationChoice.SINGLE_ROOM).build();

        InMemoryCandidateRepository candidateRepository = new InMemoryCandidateRepository();
        CandidateService candidateService = new CandidateServiceImpl(candidateRepository);
        confirmationService = new ConfirmationServiceImpl(candidateRepository, new InMemoryConfirmationRepository());
        CheckInService checkInService = new CheckInServiceImpl(new InMemoryCheckInRepository());
        mealService = new MealServiceImpl(checkInService, confirmationService);

        checkInService.addNewCheckIn(new CheckIn(johndoe.getCandidateId(), LocalDateTime.of          (2017, 10, 26, 23, 30)));
        checkInService.addNewCheckIn(new CheckIn(arthurleroi.getCandidateId(), LocalDateTime.of      (2017, 10, 26, 18, 30)));
        checkInService.addNewCheckIn(new CheckIn(rajeshkootrapoli.getCandidateId(), LocalDateTime.of (2017, 10, 26, 18, 30)));
        checkInService.addNewCheckIn(new CheckIn(sheldoncooper.getCandidateId(), LocalDateTime.of    (2017, 10, 26, 18, 30)));
        checkInService.addNewCheckIn(new CheckIn(leonardhofstadter.getCandidateId(), LocalDateTime.of(2017, 10, 26, 18, 30)));
        checkInService.addNewCheckIn(new CheckIn(howardwolowitz.getCandidateId(), LocalDateTime.of   (2017, 10, 26, 18, 30)));
        checkInService.addNewCheckIn(new CheckIn(leodagan.getCandidateId(), LocalDateTime.of         (2017, 10, 26, 18, 30)));
        checkInService.addNewCheckIn(new CheckIn(karadoc.getCandidateId(), LocalDateTime.of          (2017, 10, 26, 18, 30)));
        checkInService.addNewCheckIn(new CheckIn(perceval.getCandidateId(), LocalDateTime.of         (2017, 10, 26, 18, 30)));

        candidateService.add(johndoe);
        candidateService.add(arthurleroi);
        candidateService.add(rajeshkootrapoli);
        candidateService.add(sheldoncooper);
        candidateService.add(leonardhofstadter);
        candidateService.add(howardwolowitz);
        candidateService.add(leodagan);
        candidateService.add(karadoc);
        candidateService.add(perceval);

        confirmWithDefaultAccomodationAndPayment(JOHN_DOE_FR);
        confirmWithDefaultAccomodationAndPayment(ARTHUR_LEROI_FR);
        confirmWithDefaultAccomodationAndPayment(RAJESH_KOOTRAPOLI_COM);
        confirmWithDefaultAccomodationAndPayment(SHELDON_COOPER_COM);
        confirmWithDefaultAccomodationAndPayment(LEONARD_HOFSTADTER_COM);
        confirmWithDefaultAccomodationAndPayment(HOWARD_WOLOWITZ_COM);
        confirmWithDefaultAccomodationAndPayment(LEO_DAGAN_FR);
        confirmWithDefaultAccomodationAndPayment(KARA_DOC_FR);
        confirmWithDefaultAccomodationAndPayment(PERSE_VAL_FR);

    }

    private void confirmWithDefaultAccomodationAndPayment(String candidateEmail) {
        confirmationService.confirm(candidateEmail,LocalDate.now(), Payment.TRANSFER, AccommodationChoice.SINGLE_ROOM);
    }

    @Test
    public void should_have_number_of_covers_by_diet() throws Exception {
        confirmationService.addDiet(JOHN_DOE_FR, Diet.VEGAN);
        confirmationService.addDiet(ARTHUR_LEROI_FR, Diet.VEGAN);
        confirmationService.addDiet(RAJESH_KOOTRAPOLI_COM, Diet.VEGAN);
        confirmationService.addDiet(SHELDON_COOPER_COM, Diet.PESCATARIAN);
        confirmationService.addDiet(LEONARD_HOFSTADTER_COM, Diet.PESCATARIAN);

        Catering cateringOrdering = mealService.generateOrder();

        assertThat(cateringOrdering.numberOfCover(MealTime.THURSDAY_NIGHT, VEGAN)).isEqualTo(Quantity.of(2));
        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NOON, VEGAN)).isEqualTo(Quantity.of(3));
        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NIGHT, VEGAN)).isEqualTo(Quantity.of(3));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NOON, VEGAN)).isEqualTo(Quantity.of(3));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NIGHT, VEGAN)).isEqualTo(Quantity.of(3));
        assertThat(cateringOrdering.numberOfCover(MealTime.SUNDAY_NOON, VEGAN)).isEqualTo(Quantity.of(3));

        assertThat(cateringOrdering.numberOfCover(MealTime.THURSDAY_NIGHT, PESCATARIAN)).isEqualTo(Quantity.of(2));
        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NOON, PESCATARIAN)).isEqualTo(Quantity.of(2));
        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NIGHT, PESCATARIAN)).isEqualTo(Quantity.of(2));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NOON, PESCATARIAN)).isEqualTo(Quantity.of(2));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NIGHT, PESCATARIAN)).isEqualTo(Quantity.of(2));
        assertThat(cateringOrdering.numberOfCover(MealTime.SUNDAY_NOON, PESCATARIAN)).isEqualTo(Quantity.of(2));

        assertThat(cateringOrdering.numberOfCover(MealTime.THURSDAY_NIGHT, NORMAL)).isEqualTo(Quantity.of(4));
        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NOON, NORMAL)).isEqualTo(Quantity.of(4));
        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NIGHT, NORMAL)).isEqualTo(Quantity.of(4));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NOON, NORMAL)).isEqualTo(Quantity.of(4));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NIGHT, NORMAL)).isEqualTo(Quantity.of(4));
        assertThat(cateringOrdering.numberOfCover(MealTime.SUNDAY_NOON, NORMAL)).isEqualTo(Quantity.of(4));

    }

}
