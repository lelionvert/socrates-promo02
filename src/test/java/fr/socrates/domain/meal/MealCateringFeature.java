package fr.socrates.domain.meal;

import fr.socrates.common.Printer;
import fr.socrates.domain.attendee.ConfirmationService;
import fr.socrates.domain.attendee.ConfirmationServiceImpl;
import fr.socrates.domain.candidate.Candidate;
import fr.socrates.domain.candidate.CandidateService;
import fr.socrates.domain.candidate.CandidateServiceImpl;
import fr.socrates.domain.checkin.CheckInService;
import fr.socrates.domain.checkin.CheckInServiceImpl;
import fr.socrates.infra.repositories.InMemoryCandidateRepository;
import fr.socrates.infra.repositories.InMemoryCheckInRepository;
import fr.socrates.infra.repositories.InMemoryConfirmationRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

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
    private Candidate perseval;
    private ConfirmationService confirmationService;
    private MealService mealService;

    @Mock
    private Printer console;

    @Before
    public void setUp() throws Exception {
        johndoe = Candidate.withEmail(JOHN_DOE_FR);
        arthurleroi = Candidate.withEmail(ARTHUR_LEROI_FR);
        rajeshkootrapoli = Candidate.withEmail(RAJESH_KOOTRAPOLI_COM);
        sheldoncooper = Candidate.withEmail(SHELDON_COOPER_COM);
        leonardhofstadter = Candidate.withEmail(LEONARD_HOFSTADTER_COM);
        howardwolowitz = Candidate.withEmail(HOWARD_WOLOWITZ_COM);
        leodagan = Candidate.withEmail(LEO_DAGAN_FR);
        karadoc = Candidate.withEmail(KARA_DOC_FR);
        perseval = Candidate.withEmail(PERSE_VAL_FR);

        InMemoryCandidateRepository candidateRepository = new InMemoryCandidateRepository();
        CandidateService candidateService = new CandidateServiceImpl(candidateRepository);
        confirmationService = new ConfirmationServiceImpl(candidateRepository, new InMemoryConfirmationRepository());
        CheckInService checkInService = new CheckInServiceImpl(new InMemoryCheckInRepository());
        mealService = new MealServiceImpl(checkInService, confirmationService);

        candidateService.add(johndoe);
        candidateService.add(arthurleroi);
        candidateService.add(rajeshkootrapoli);
        candidateService.add(sheldoncooper);
        candidateService.add(leonardhofstadter);
        candidateService.add(howardwolowitz);
        candidateService.add(leodagan);
        candidateService.add(karadoc);
        candidateService.add(perseval);

        confirmationService.confirm(JOHN_DOE_FR);
        confirmationService.confirm(ARTHUR_LEROI_FR);
        confirmationService.confirm(RAJESH_KOOTRAPOLI_COM);
        confirmationService.confirm(SHELDON_COOPER_COM);
        confirmationService.confirm(LEONARD_HOFSTADTER_COM);
        confirmationService.confirm(HOWARD_WOLOWITZ_COM);
        confirmationService.confirm(LEO_DAGAN_FR);
        confirmationService.confirm(KARA_DOC_FR);
        confirmationService.confirm(PERSE_VAL_FR);

    }

    @Test
    public void should_have_number_of_covers_by_diet() throws Exception {
        confirmationService.addDiet(JOHN_DOE_FR, Diet.VEGAN);
        confirmationService.addDiet(ARTHUR_LEROI_FR, Diet.VEGAN);
        confirmationService.addDiet(RAJESH_KOOTRAPOLI_COM, Diet.VEGAN);
        confirmationService.addDiet(SHELDON_COOPER_COM, Diet.PESCATARIAN);
        confirmationService.addDiet(LEONARD_HOFSTADTER_COM, Diet.PESCATARIAN);

        Map<MealTime, Map<Diet, Long>> dietMap = mealService.getCoversByDiet();
        Map<MealTime, Map<Diet, Long>> expectedDietMap = createExpectedDietMap();

        assertThat(dietMap).isEqualTo(expectedDietMap);
    }

    private Map<MealTime, Map<Diet, Long>> createExpectedDietMap() {
        Map<MealTime, Map<Diet, Long>> expectedDietMap = new HashMap<>();

        Map<Diet, Long> numberOfDietsFridayNoon = new HashMap<>();
        numberOfDietsFridayNoon.put(Diet.VEGAN, 3L);
        numberOfDietsFridayNoon.put(Diet.PESCATARIAN, 2L);
        numberOfDietsFridayNoon.put(Diet.NORMAL, 4L);
        expectedDietMap.put(MealTime.FRIDAY_NOON, numberOfDietsFridayNoon);

        Map<Diet, Long> numberOfDietsFridayNight = new HashMap<>();
        numberOfDietsFridayNight.put(Diet.VEGAN, 3L);
        numberOfDietsFridayNight.put(Diet.PESCATARIAN, 2L);
        numberOfDietsFridayNight.put(Diet.NORMAL, 4L);
        expectedDietMap.put(MealTime.FRIDAY_NIGHT, numberOfDietsFridayNight);

        Map<Diet, Long> numberOfDietsSaturdayNoon = new HashMap<>();
        numberOfDietsSaturdayNoon.put(Diet.VEGAN, 3L);
        numberOfDietsSaturdayNoon.put(Diet.PESCATARIAN, 2L);
        numberOfDietsSaturdayNoon.put(Diet.NORMAL, 4L);
        expectedDietMap.put(MealTime.SATURDAY_NOON, numberOfDietsSaturdayNoon);

        Map<Diet, Long> numberOfDietsSaturdayNight = new HashMap<>();
        numberOfDietsSaturdayNight.put(Diet.VEGAN, 3L);
        numberOfDietsSaturdayNight.put(Diet.PESCATARIAN, 2L);
        numberOfDietsSaturdayNight.put(Diet.NORMAL, 4L);
        expectedDietMap.put(MealTime.SATURDAY_NIGHT, numberOfDietsSaturdayNight);

        Map<Diet, Long> numberOfDietsSundayNoon = new HashMap<>();
        numberOfDietsSundayNoon.put(Diet.VEGAN, 3L);
        numberOfDietsSundayNoon.put(Diet.PESCATARIAN, 2L);
        numberOfDietsSundayNoon.put(Diet.NORMAL, 4L);
        expectedDietMap.put(MealTime.SUNDAY_NOON, numberOfDietsSundayNoon);

        return expectedDietMap;
    }
}
