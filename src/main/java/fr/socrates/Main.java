package fr.socrates;

import fr.socrates.common.Printer;
import fr.socrates.domain.sponsor.Sponsor;
import fr.socrates.domain.sponsor.SponsorRespository;
import fr.socrates.domain.sponsor.SponsorService;
import fr.socrates.infra.printers.ConsolePrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SponsorService sponsorService = new SponsorService(new FakeSponsorRespository(), new ConsolePrinter());
        Scanner scanner = new Scanner(System.in);
        final String menu = "Yo Houssam, tu veux ajouter un candidat (1) ou les lister (2), un sponsor (3) ou les lister(4) ou un checkin (5) ou le nombre de repas froids (6) ? (0) si tu veux sortir !";
        System.out.println(menu);
        String choice = scanner.next();
        while(choice != "0"){
            switch (choice) {
                case "2":
                    listerSponsors(sponsorService);
                    System.out.println(menu);
                    choice = scanner.next();
                    break;
                case "3":
                    sponsorService.addSponsor(createSponsor(scanner));
                    System.out.println(menu);
                    choice = scanner.next();
                    break;
                default:
                    System.out.println(menu);
                    choice = scanner.next();
                    break;
            }
        }

    }

    private static void listerSponsors(SponsorService sponsorService) {
        sponsorService.print();
    }

    private static Sponsor createSponsor(Scanner scanner) {
        String[] sponsorInfos = {"Nom", "SIRET", "SIREN", "Représentant", "Contact Email", "Montant du sponsoring"};
        List<String> sponsorInputs = new ArrayList<>();

        for (String sponsorInfo : sponsorInfos) {
            System.out.println(sponsorInfo + ":");
            sponsorInputs.add(scanner.next());
        }
        FakePrinter printer = new FakePrinter();
        SponsorService sponsorService = new SponsorService(new FakeSponsorRespository(), printer);


        return new Sponsor.SponsorBuilder()
                .withName(sponsorInputs.get(0))
                .withSIRET(sponsorInputs.get(1))
                .withSIREN(sponsorInputs.get(2))
                .withContractRepresentative(sponsorInputs.get(3))
                .withContact(sponsorInputs.get(4))
                .withAmountOfSponsoring(Double.parseDouble(sponsorInputs.get(5)))
                .createSponsor();

    }

    private static class FakeSponsorRespository implements SponsorRespository {
        private final List<Sponsor> sponsors;

        public FakeSponsorRespository() {
            sponsors = new ArrayList<>();
        }

        public void addSponsor(Sponsor sponsor) {
            sponsors.add(sponsor);
        }

        public List<Sponsor> getSponsorsList() {
            return sponsors;
        }
    }

    private static class FakePrinter implements Printer{
        private List<String> buf;

        public FakePrinter() {
            this.buf = new ArrayList<>();
        }

        @Override
        public void print(String toPrint) {
            buf.add(toPrint);
        }

        public String flush() {
            StringBuilder stringToFlush = new StringBuilder();
            for (int i = 0; i < buf.size(); i++) {
                stringToFlush.append(buf.get(i));
                if (i < buf.size() - 1)
                    stringToFlush.append("; ");
            }
            return stringToFlush.toString();
        }
    }
}
