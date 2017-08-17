package fr.socrates;

import fr.socrates.common.Printer;
import fr.socrates.domain.candidate.*;
import fr.socrates.domain.checkin.*;
import fr.socrates.domain.meal.MealService;
import fr.socrates.domain.meal.MealServiceImpl;
import fr.socrates.domain.sponsor.Sponsor;
import fr.socrates.domain.sponsor.SponsorRespository;
import fr.socrates.domain.sponsor.SponsorService;
import fr.socrates.domain.sponsor.SponsorServiceImpl;
import fr.socrates.infra.printers.ConsolePrinter;
import fr.socrates.infra.repositories.InMemoryCandidateRepository;
import fr.socrates.infra.repositories.InMemoryCheckInRepository;
import fr.socrates.infra.repositories.InMemorySponsorRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        final String MENU_MESSAGE = "Yo Houssam, tu veux: \n 1- Ajouter un candidat \n 2- Lister les candidats \n 3- Ajouter un sponsor \n 4- Lister les sponsors \n 5- Ajouter un checkin \n 6- Le nombre de repas froids ? \n 0- Quitter!";
        final String ENDING_MESSAGE = "Merci d'avoir utilisé ce programme des Mi-Ours, Mi-Scorpions, et re mi-ours derrière !";
        final String ZERO = "0";
        final String ONE = "1";
        final String TWO = "2";
        final String THREE = "3";
        final String FOUR = "4";
        final String FIVE = "5";
        final String SIX = "6";

        CandidateRepository inMemoryCandidateRepository = new InMemoryCandidateRepository();
        CheckInRepository inMemoryCheckInRepository = new InMemoryCheckInRepository();
        SponsorRespository inMemorySponsorRepository = new InMemorySponsorRepository();

        Printer consolePrinter = new ConsolePrinter();

        SponsorService sponsorService = new SponsorServiceImpl(inMemorySponsorRepository);
        CandidateService candidateService = new CandidateServiceImpl(inMemoryCandidateRepository);
        CheckInService checkInService = new CheckInServiceImpl(inMemoryCheckInRepository, consolePrinter);
        MealService mealService = new MealServiceImpl(checkInService);

        Scanner scanner = new Scanner(System.in);

        final String menu_message = MENU_MESSAGE;
        consolePrinter.print(menu_message);
        String choice = scanner.next();
        while (choice != ZERO) {
            switch (choice) {
                case ONE:
                    consolePrinter.print("Ajouter un candidat : ");
                    candidateService.add(createCandidate(scanner, consolePrinter));
                    consolePrinter.print(MENU_MESSAGE);
                    choice = scanner.next();
                    break;
                case TWO:
                    consolePrinter.print("Liste des candidats :");
                    CandidateFormatter candidateFormatter = new CandidateFormatter();
                    consolePrinter.print(candidateFormatter.format(candidateService.getRegisteredCandidates()));
                    consolePrinter.print(MENU_MESSAGE);
                    choice = scanner.next();
                    break;
                case THREE:
                    consolePrinter.print("Ajouter un sponsor : ");
                    sponsorService.addSponsor(createSponsor(scanner, consolePrinter));
                    consolePrinter.print(MENU_MESSAGE);
                    choice = scanner.next();
                    break;
                case FOUR:
                    consolePrinter.print("Liste des sponsors :");
                    consolePrinter.print(listSponsors(sponsorService));
                    consolePrinter.print(MENU_MESSAGE);
                    choice = scanner.next();
                    break;
                case FIVE:
                    consolePrinter.print("Ajouter un checkin :");
                    boolean exists = checkInService.addNewCheckIn(createCheckin(scanner, consolePrinter));
                    if (exists) {
                        consolePrinter.print("Checkin mis à jour.");
                    }
                    consolePrinter.print(MENU_MESSAGE);
                    choice = scanner.next();
                    break;
                case SIX:
                    consolePrinter.print("Nombre de repas froids :");
                    displayColdMealCount(mealService, consolePrinter);
                    consolePrinter.print(MENU_MESSAGE);
                    choice = scanner.next();
                    break;
                case ZERO:
                    consolePrinter.print(ENDING_MESSAGE);
                    choice = ZERO;
                    break;
                default:
                    consolePrinter.print(MENU_MESSAGE);
                    choice = scanner.next();
                    break;
            }
        }
    }


    private static CheckIn createCheckin(Scanner scanner, Printer consolePrinter) {
        consolePrinter.print("Format de saisie: john=2017-12-03T23:15:30");

        String[] checkInArgument = scanner.next().split("=");
        AttendeeId attendeeId = new AttendeeId(checkInArgument[0]);
        LocalDateTime checkInDateTime = LocalDateTime.parse(checkInArgument[1], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return new CheckIn(attendeeId, checkInDateTime);
    }

    private static Candidate createCandidate(Scanner scanner, Printer consolePrinter) {
        String[] candidateInfos = {"eMail"};
        List<String> candidateInputs = new ArrayList<>();

        for (String candidateInfo : candidateInfos) {
            consolePrinter.print(candidateInfo + ":");
            candidateInputs.add(scanner.next());
        }
        return Candidate.withEmail(candidateInputs.get(0));
    }

    private static List<String> listSponsors(SponsorService sponsorService) {
        final List<Sponsor> sponsorsList = sponsorService.getSponsorsList();
        if (sponsorsList.isEmpty())
            return Collections.singletonList("Aucun element dans la liste");
        else
            return sponsorsList.stream().map(Sponsor::toString).collect(Collectors.toList());
    }


    private static Sponsor createSponsor(Scanner scanner, Printer consolePrinter) {
        String[] sponsorInfos = {"Nom", "SIRET", "SIREN", "Représentant", "Contact Email", "Montant du sponsoring"};
        List<String> sponsorInputs = new ArrayList<>();

        for (String sponsorInfo : sponsorInfos) {
            consolePrinter.print(sponsorInfo + ":");
            sponsorInputs.add(scanner.next());
        }

        return new Sponsor.SponsorBuilder()
                .withName(sponsorInputs.get(0))
                .withSIRET(sponsorInputs.get(1))
                .withSIREN(sponsorInputs.get(2))
                .withContractRepresentative(sponsorInputs.get(3))
                .withContact(sponsorInputs.get(4))
                .withAmountOfSponsoring(Double.parseDouble(sponsorInputs.get(5)))
                .createSponsor();

    }
}
