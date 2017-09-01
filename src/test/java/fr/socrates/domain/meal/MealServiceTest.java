package fr.socrates.domain.meal;

import fr.socrates.domain.CandidateId;
import fr.socrates.domain.attendee.ConfirmationService;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.EMail;
import fr.socrates.domain.checkin.CheckInService;
import fr.socrates.domain.common.AccommodationChoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static fr.socrates.domain.meal.Diet.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MealServiceTest {
    private MealService mealService;

    @Mock
    private CheckInService checkInService;

    @Mock
    private ConfirmationService confirmationService;

    @Before
    public void setUp() throws Exception {
        mealService = new MealServiceImpl(checkInService, confirmationService);
        when(checkInService.isCandidatePresentAt(Mockito.any(), Mockito.any())).thenReturn(true);
    }

    @Test
    public void should_no_produce_an_order_without_attendes() throws Exception {
        when(confirmationService.getAttendee()).thenReturn(Collections.emptyList());

        Catering cateringOrdering = mealService.generateOrder();

        Catering expectedCateringOrder = new Catering(Collections.emptyMap());
        assertThat(cateringOrdering).isEqualTo(expectedCateringOrder);
    }

    @Test
    public void should_get_number_of_covers_by_diet_by_mealtime_when_only_one_attendee() throws Exception {
        List<Candidate> attendees = Collections.singletonList(
                new Candidate.CandidateBuilder()
                        .withEmail(EMail.of("kara@doc.fr"))
                        .withDiet(Diet.NORMAL)
                        .withCandidateId(new CandidateId("1"))
                        .withOneAccommodationChoice(AccommodationChoice.SINGLE_ROOM)
                        .build());
        when(confirmationService.getAttendee()).thenReturn(attendees);

        Catering cateringOrdering = mealService.generateOrder();

        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NOON, NORMAL)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NIGHT, NORMAL)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NOON, NORMAL)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NIGHT, NORMAL)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.SUNDAY_NOON, NORMAL)).isEqualTo(Quantity.of(1));
    }

    @Test
    public void should_get_number_of_covers_by_diet_by_mealtime_for_several_attendees() throws Exception {
        List<Candidate> attendees = Arrays.asList(
                new Candidate.CandidateBuilder().withEmail(EMail.of("kara@doc.fr")).withDiet(Diet.NORMAL).withCandidateId(new CandidateId("1")).withOneAccommodationChoice(AccommodationChoice.SINGLE_ROOM).build(),
                new Candidate.CandidateBuilder().withEmail(EMail.of("perce@val.fr")).withDiet(VEGAN).withCandidateId(new CandidateId("2")).withOneAccommodationChoice(AccommodationChoice.SINGLE_ROOM).build());
        when(confirmationService.getAttendee()).thenReturn(attendees);

        Catering cateringOrdering = mealService.generateOrder();

        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NOON, VEGAN)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NIGHT, VEGAN)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NOON, VEGAN)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NIGHT, VEGAN)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.SUNDAY_NOON, VEGAN)).isEqualTo(Quantity.of(1));

        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NOON, NORMAL)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NIGHT, NORMAL)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NOON, NORMAL)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NIGHT, NORMAL)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.SUNDAY_NOON, NORMAL)).isEqualTo(Quantity.of(1));

    }

    @Test
    public void should_get_number_of_covers_by_diet_by_mealtime_for_several_attendees_same_diet() throws Exception {
        List<Candidate> attendees = Arrays.asList(
                new Candidate.CandidateBuilder().withEmail(EMail.of("kara@doc.fr")).withDiet(VEGAN).withCandidateId(new CandidateId("1")).withOneAccommodationChoice(AccommodationChoice.SINGLE_ROOM).build(),
                new Candidate.CandidateBuilder().withEmail(EMail.of("perce@val.fr")).withDiet(VEGAN).withCandidateId(new CandidateId("2")).withOneAccommodationChoice(AccommodationChoice.SINGLE_ROOM).build());
        when(confirmationService.getAttendee()).thenReturn(attendees);

        Catering cateringOrdering = mealService.generateOrder();

        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NOON, VEGAN)).isEqualTo(Quantity.of(2));
        assertThat(cateringOrdering.numberOfCover(MealTime.FRIDAY_NIGHT, VEGAN)).isEqualTo(Quantity.of(2));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NOON, VEGAN)).isEqualTo(Quantity.of(2));
        assertThat(cateringOrdering.numberOfCover(MealTime.SATURDAY_NIGHT, VEGAN)).isEqualTo(Quantity.of(2));
        assertThat(cateringOrdering.numberOfCover(MealTime.SUNDAY_NOON, VEGAN)).isEqualTo(Quantity.of(2));
    }

    @Test
    public void should_get_number_of_covers_by_diet_by_mealtime_for_several_attendees_except_thursday_for_one_attendee() throws Exception {
        List<Candidate> attendees = Arrays.asList(
                new Candidate.CandidateBuilder().withEmail(EMail.of("kara@doc.fr")).withDiet(Diet.NORMAL).withCandidateId(new CandidateId("1")).withOneAccommodationChoice(AccommodationChoice.SINGLE_ROOM).build(),
                new Candidate.CandidateBuilder().withEmail(EMail.of("perce@val.fr")).withDiet(VEGAN).withCandidateId(new CandidateId("2")).withOneAccommodationChoice(AccommodationChoice.SINGLE_ROOM).build(),
                new Candidate.CandidateBuilder().withEmail(EMail.of("dame@seli.fr")).withDiet(VEGAN).withCandidateId(new CandidateId("3")).withOneAccommodationChoice(AccommodationChoice.SINGLE_ROOM).build(),
                new Candidate.CandidateBuilder().withEmail(EMail.of("roi@loth.fr")).withDiet(VEGAN).withCandidateId(new CandidateId("4")).withOneAccommodationChoice(AccommodationChoice.SINGLE_ROOM).build(),
                new Candidate.CandidateBuilder().withEmail(EMail.of("bo@hort.fr")).withDiet(Diet.PESCATARIAN).withCandidateId(new CandidateId("5")).withOneAccommodationChoice(AccommodationChoice.SINGLE_ROOM).build());
        when(confirmationService.getAttendee()).thenReturn(attendees);

        when(checkInService.isCandidatePresentAt(attendees.get(1).getCandidateId(), MealTime.THURSDAY_NIGHT.getDateTime())).thenReturn(false);

        when(checkInService.isCandidatePresentAt(attendees.get(1).getCandidateId(), MealTime.THURSDAY_NIGHT.getDateTime())).thenReturn(false);

        Catering cateringOrdering = mealService.generateOrder();

        assertThat(cateringOrdering.numberOfCover(MealTime.THURSDAY_NIGHT, NORMAL)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.THURSDAY_NIGHT, PESCATARIAN)).isEqualTo(Quantity.of(1));
        assertThat(cateringOrdering.numberOfCover(MealTime.THURSDAY_NIGHT, VEGAN)).isEqualTo(Quantity.of(2));
    }
}
