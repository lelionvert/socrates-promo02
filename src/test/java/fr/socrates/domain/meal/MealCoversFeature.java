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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MealCoversFeature {
    public static final String JOHN_DOE_FR = "john@doe.fr";
    public static final String ARTHUR_LEROI_FR = "arthur@leroi.fr";
    public static final String RAJESH_KOOTRAPOLI_COM = "rajesh@kootrapoli.com";
    public static final String SHELDON_COOPER_COM = "sheldon@cooper.com";
    public static final String LEONARD_HOFSTADTER_COM = "leonard@hofstadter.com";
    public static final String HOWARD_WOLOWITZ_COM = "howard@wolowitz.com";
    public static final String LEO_DAGAN_FR = "leo@dagan.fr";
    public static final String KARA_DOC_FR = "kara@doc.fr";
    public static final String PERSE_VAL_FR = "perse@val.fr";
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

        mealService.printAllMeals();

        verify(console).print("Vendredi 18 midi");
        verify(console).print("Vegan : 3");
        verify(console).print("Pescatarian : 2");
        verify(console).print("Normal : 4");

        verify(console).print("Vendredi 18 soir");
        verify(console).print("Vegan : 3");
        verify(console).print("Pescatarian : 2");
        verify(console).print("Normal : 4");

        verify(console).print("Samedi 19 midi");
        verify(console).print("Vegan : 3");
        verify(console).print("Pescatarian : 2");
        verify(console).print("Normal : 4");

        verify(console).print("Samedi 19 soir");
        verify(console).print("Vegan : 3");
        verify(console).print("Pescatarian : 2");
        verify(console).print("Normal : 4");

        verify(console).print("Dimanche 20 midi");
        verify(console).print("Vegan : 3");
        verify(console).print("Pescatarian : 2");
        verify(console).print("Normal : 4");
    }
}
