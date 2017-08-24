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
    private Candidate johndoe;
    private Candidate arthurleroi;
    private Candidate rajeshkootrapoli;
    private Candidate sheldoncooper;
    private Candidate leonardhofstadter;
    private Candidate howardwolowitz;
    private Candidate leodagan;
    private Candidate karadoc;
    private Candidate perseval;
    private String johndoeMail;
    private String arthurleroiMail;
    private String rajeshMail;
    private String sheldonMail;
    private String leonardMail;
    private String howardMail;
    private String leodaganMail;
    private String karadocMail;
    private String persevalMail;
    private ConfirmationService confirmationService;
    private MealService mealService;

    @Mock
    Printer console;

    @Before
    public void setUp() throws Exception {
        johndoeMail = "john@doe.fr";
        arthurleroiMail = "arthur@leroi.fr";
        rajeshMail = "rajesh@kootrapoli.com";
        sheldonMail = "sheldon@cooper.com";
        leonardMail = "leonard@hofstadter.com";
        howardMail = "howard@wolowitz.com";
        leodaganMail = "leo@dagan.fr";
        karadocMail = "kara@doc.fr";
        persevalMail = "perse@val.fr";

        johndoe = Candidate.withEmail(johndoeMail);
        arthurleroi = Candidate.withEmail(arthurleroiMail);
        rajeshkootrapoli = Candidate.withEmail(rajeshMail);
        sheldoncooper = Candidate.withEmail(sheldonMail);
        leonardhofstadter = Candidate.withEmail(leonardMail);
        howardwolowitz = Candidate.withEmail(howardMail);
        leodagan = Candidate.withEmail(leodaganMail);
        karadoc = Candidate.withEmail(karadocMail);
        perseval = Candidate.withEmail(persevalMail);

        InMemoryCandidateRepository candidateRepository = new InMemoryCandidateRepository();
        CandidateService candidateService = new CandidateServiceImpl(candidateRepository);
        candidateService.add(johndoe);
        candidateService.add(arthurleroi);
        candidateService.add(rajeshkootrapoli);
        candidateService.add(sheldoncooper);
        candidateService.add(leonardhofstadter);
        candidateService.add(howardwolowitz);
        candidateService.add(leodagan);
        candidateService.add(karadoc);
        candidateService.add(perseval);

        confirmationService = new ConfirmationServiceImpl(candidateRepository, new InMemoryConfirmationRepository());
        confirmationService.confirm(johndoeMail);
        confirmationService.confirm(arthurleroiMail);
        confirmationService.confirm(rajeshMail);
        confirmationService.confirm(sheldonMail);
        confirmationService.confirm(leonardMail);
        confirmationService.confirm(howardMail);
        confirmationService.confirm(leodaganMail);
        confirmationService.confirm(karadocMail);
        confirmationService.confirm(persevalMail);

        CheckInService checkInService = new CheckInServiceImpl(new InMemoryCheckInRepository());
        mealService = new MealServiceImpl(checkInService);
    }

    @Test
    public void should_have_number_of_covers_by_diet() throws Exception {
        confirmationService.addDiet(johndoeMail, Diets.VEGAN);
        confirmationService.addDiet(arthurleroiMail, Diets.VEGAN);
        confirmationService.addDiet(rajeshMail, Diets.VEGAN);
        confirmationService.addDiet(sheldonMail, Diets.PESCATARIAN);
        confirmationService.addDiet(leonardMail, Diets.PESCATARIAN);
        confirmationService.addDiet(howardMail);
        confirmationService.addDiet(leodaganMail);
        confirmationService.addDiet(karadocMail);
        confirmationService.addDiet(persevalMail);

        mealService.printAllMeals();

        verify(console).print("Vendredi 18 midi");
        verify(console).print("Vegan : 3");
        verify(console).print("pescatarian : 2");
        verify(console).print("Normal : 4");

        verify(console).print("Vendredi 18 soir");
        verify(console).print("Vegan : 3");
        verify(console).print("pescatarian : 2");
        verify(console).print("Normal : 4");

        verify(console).print("Samedi 19 midi");
        verify(console).print("Vegan : 3");
        verify(console).print("pescatarian : 2");
        verify(console).print("Normal : 4");

        verify(console).print("Samedi 19 midi");
        verify(console).print("Vegan : 3");
        verify(console).print("pescatarian : 2");
        verify(console).print("Normal : 4");

        verify(console).print("Samedi 19 soir");
        verify(console).print("Vegan : 3");
        verify(console).print("pescatarian : 2");
        verify(console).print("Normal : 4");

        verify(console).print("Dimanche 20 midi");
        verify(console).print("Vegan : 3");
        verify(console).print("pescatarian : 2");
        verify(console).print("Normal : 4");
    }
}
