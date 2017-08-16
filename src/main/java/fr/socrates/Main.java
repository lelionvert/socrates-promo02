package fr.socrates;

import fr.socrates.common.Printer;
import fr.socrates.domain.candidate.*;
import fr.socrates.domain.checkin.*;
import fr.socrates.domain.sponsor.Sponsor;
import fr.socrates.domain.sponsor.SponsorRespository;
import fr.socrates.domain.sponsor.SponsorService;
import fr.socrates.infra.printers.ConsolePrinter;
import fr.socrates.infra.repositories.InMemoryCandidateRepository;
import fr.socrates.infra.repositories.InMemoryCheckInRepository;
import fr.socrates.infra.repositories.InMemorySponsorRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final String MENU_MESSAGE = "Yo Houssam, tu veux: \n 1- Ajouter un candidat \n 2- Lister les candidats \n 3- Ajouter un sponsor \n 4- Lister les sponsors \n 5- Ajouter un checkin \n 6- Le nombre de repas froids (6) ? \n 0- Quitter!";
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

        SponsorService sponsorService = new SponsorService(inMemorySponsorRepository, consolePrinter);
        CandidateService candidateService = new CandidateServiceImpl(inMemoryCandidateRepository, consolePrinter);
        CheckInService checkInService = new CheckInServiceImpl(inMemoryCheckInRepository, consolePrinter);

        Scanner scanner = new Scanner(System.in);

        System.out.println(MENU_MESSAGE);
        String choice = scanner.next();
        while (choice != ZERO) {
            switch (choice) {
                case ONE:
                    candidateService.add(createCandidate(scanner));
                    System.out.println(MENU_MESSAGE);
                    choice = scanner.next();
                    break;
                case TWO:
                    CandidateFormatter candidateFormatter = new CandidateFormatter();
                    consolePrinter.print(candidateFormatter.format(candidateService.getRegisteredCandidates()));
                    System.out.println(MENU_MESSAGE);
                    choice = scanner.next();
                    break;
                case THREE:
                    sponsorService.addSponsor(createSponsor(scanner));
                    System.out.println(MENU_MESSAGE);
                    choice = scanner.next();
                    break;
                case FOUR:
                    listSponsors(sponsorService);
                    System.out.println(MENU_MESSAGE);
                    choice = scanner.next();
                    break;
                case FIVE:
                    checkInService.addNewCheckIn(createCheckin(scanner));
                    System.out.println(MENU_MESSAGE);
                    choice = scanner.next();
                    break;
                case SIX:
                    listCheckin(checkInService);
                    System.out.println(MENU_MESSAGE);
                    choice = scanner.next();
                    break;
                case ZERO:
                    System.out.println(ENDING_MESSAGE);
                    choice = ZERO;
                    break;
                default:
                    System.out.println(MENU_MESSAGE);
                    choice = scanner.next();
                    break;
            }
        }
    }


    private static CheckIn createCheckin(Scanner scanner) {
        System.out.println("Format de saisie: john=2017-12-03T23:15:30");

        String[] checkInArgument = scanner.next().split("=");
        ParticipantId participantId = new ParticipantId(checkInArgument[0]);
        LocalDateTime checkInDateTime = LocalDateTime.parse(checkInArgument[1], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return new CheckIn(participantId, checkInDateTime);
}

    private static Candidate createCandidate(Scanner scanner) {
        String[] candidateInfos = {"eMail"};
        List<String> candidateInputs = new ArrayList<>();

        for (String candidateInfo : candidateInfos) {
            System.out.println(candidateInfo + ":");
            candidateInputs.add(scanner.next());
        }
        return Candidate.withEmail(candidateInputs.get(0));
    }

    private static void listCheckin(CheckInService checkInService) {
        checkInService.print();
    }
    private static void listSponsors(SponsorService sponsorService) {
        sponsorService.print();
    }

    private static Sponsor createSponsor(Scanner scanner) {
        String[] sponsorInfos = {"Nom", "SIRET", "SIREN", "Représentant", "Contact Email", "Montant du sponsoring"};
        List<String> sponsorInputs = new ArrayList<>();

        for (String sponsorInfo : sponsorInfos) {
            System.out.println(sponsorInfo + ":");
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
