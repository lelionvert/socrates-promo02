package fr.socrates;

import fr.socrates.common.Printer;
import fr.socrates.domain.CandidateId;
import fr.socrates.domain.attendee.ConfirmationRepository;
import fr.socrates.domain.attendee.ConfirmationService;
import fr.socrates.domain.attendee.ConfirmationServiceImpl;
import fr.socrates.domain.attendee.Payment;
import fr.socrates.domain.candidate.*;
import fr.socrates.domain.checkin.CheckIn;
import fr.socrates.domain.checkin.CheckInRepository;
import fr.socrates.domain.checkin.CheckInService;
import fr.socrates.domain.checkin.CheckInServiceImpl;
import fr.socrates.domain.common.AccommodationChoice;
import fr.socrates.domain.meal.MealService;
import fr.socrates.domain.meal.MealServiceImpl;
import fr.socrates.domain.sponsor.Sponsor;
import fr.socrates.domain.sponsor.SponsorRepository;
import fr.socrates.domain.sponsor.SponsorService;
import fr.socrates.domain.sponsor.SponsorServiceImpl;
import fr.socrates.infra.printers.ConsolePrinter;
import fr.socrates.infra.repositories.InMemoryCandidateRepository;
import fr.socrates.infra.repositories.InMemoryCheckInRepository;
import fr.socrates.infra.repositories.InMemoryConfirmationRepository;
import fr.socrates.infra.repositories.InMemorySponsorRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) {
        final String MENU_MESSAGE = "Yo Houssam, tu veux: \n 1- Ajouter un candidat \n 2- Lister les candidats \n 3- Ajouter un sponsor \n 4- Lister les sponsors \n 5- Ajouter un checkin \n 6- Le nombre de repas froids ? \n 7- Pour confirmer un candidat \n 8- Liste des confirmations \n 0- Quitter!";
        final String ENDING_MESSAGE = "Merci d'avoir utilisé ce programme des Mi-Ours, Mi-Scorpions, et re mi-ours derrière !";
        final String ZERO = "0";
        final String ONE = "1";
        final String TWO = "2";
        final String THREE = "3";
        final String FOUR = "4";
        final String FIVE = "5";
        final String SIX = "6";
        final String SEVEN = "7";
        final String EIGHT = "8";

        CandidateRepository inMemoryCandidateRepository = new InMemoryCandidateRepository();
        CheckInRepository inMemoryCheckInRepository = new InMemoryCheckInRepository();
        SponsorRepository inMemorySponsorRepository = new InMemorySponsorRepository();
        ConfirmationRepository inMemoryConfirmationRepository = new InMemoryConfirmationRepository();

        Printer consolePrinter = new ConsolePrinter();

        SponsorService sponsorService = new SponsorServiceImpl(inMemorySponsorRepository);
        CandidateService candidateService = new CandidateServiceImpl(inMemoryCandidateRepository);
        CheckInService checkInService = new CheckInServiceImpl(inMemoryCheckInRepository);
        MealService mealService = new MealServiceImpl(checkInService);
        ConfirmationService confirmationService = new ConfirmationServiceImpl(inMemoryCandidateRepository, inMemoryConfirmationRepository);

        Scanner scanner = new Scanner(System.in);

        consolePrinter.print(MENU_MESSAGE);
        String choice = scanner.next();
        while (!choice.equals(ZERO)) {
            switch (choice) {
                case ONE:
                    consolePrinter.print("Ajouter un candidat : ");
                    try {
                        candidateService.add(createCandidate(scanner, consolePrinter));
                    } catch (CandidateException.CandidatePersisteDataException | CandidateException.CandidateExistingException e) {
                        consolePrinter.print(e.toString());
                    }
                    consolePrinter.print(MENU_MESSAGE);
                    choice = scanner.next();
                    break;
                case TWO:
                    consolePrinter.print("Liste des candidats :");
                    consolePrinter.print(format(candidateService.getRegisteredCandidates()));
                    consolePrinter.print(MENU_MESSAGE);
                    choice = scanner.next();
                    break;
                case THREE:
                    consolePrinter.print("Ajouter un sponsor : ");
                    try {
                        sponsorService.addSponsor(createSponsor(scanner, consolePrinter));
                    } catch (IllegalArgumentException e) {
                        consolePrinter.print(" \n" +
                                " /!\\ Erreur d'ajout du sponsor. Les SIREN et/ou SIRET sont invalides /!\\ \n");
                    }
                    consolePrinter.print(MENU_MESSAGE);
                    choice = scanner.next();
                    break;
                case FOUR:
                    consolePrinter.print("Liste des sponsors :");
                    consolePrinter.print(format(sponsorService.getSponsorsList()));
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
                case SEVEN:
                    consolePrinter.print(format(candidateService.getRegisteredCandidates()));
                    consolePrinter.print("Tape l'email du candidat à confirmer");
                    boolean confirmation = confirmationService.confirm(scanner.next(), LocalDate.now(), Payment.TRANSFER, AccommodationChoice.SINGLE_ROOM);
                    if (!confirmation)
                        consolePrinter.print("Erreur la confirmation a echoue");
                    consolePrinter.print(MENU_MESSAGE);
                    choice = scanner.next();
                    break;
                case EIGHT:
                    consolePrinter.print("Liste des confirmes :");
                    consolePrinter.print(format(confirmationService.getListAttendee()));
                    consolePrinter.print(MENU_MESSAGE);
                    choice = scanner.next();
                    break;
                default:
                    consolePrinter.print(MENU_MESSAGE);
                    choice = scanner.next();
                    break;
            }
        }
        consolePrinter.print(ENDING_MESSAGE);
    }

    private static void displayColdMealCount(MealService mealService, Printer consolePrinter) {
        consolePrinter.print(String.valueOf(mealService.countColdMeal()));
    }

    private static CheckIn createCheckin(Scanner scanner, Printer consolePrinter) {
        consolePrinter.print("Format de saisie: john=2017-12-03T23:15:30");

        String[] checkInArgument = scanner.next().split("=");
        CandidateId candidateId = new CandidateId(checkInArgument[0]);
        LocalDateTime checkInDateTime = LocalDateTime.parse(checkInArgument[1], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return new CheckIn(candidateId, checkInDateTime);
    }

    private static Candidate createCandidate(Scanner scanner, Printer consolePrinter) {
        String[] candidateInfos = {"eMail"};
        List<String> candidateInputs = new ArrayList<>();

        for (String candidateInfo : candidateInfos) {
            consolePrinter.print(candidateInfo + ":");
            candidateInputs.add(scanner.next());
        }
        // TODO ask for choices for candidate
        return Candidate.singleRoomWithEmail(candidateInputs.get(0));
    }


    private static <T> List<String> format(List<T> list) {
        if (list.isEmpty())
            return Collections.singletonList("Aucun element dans la liste");
        else
            return list.stream().map(T::toString).collect(Collectors.toList());
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
