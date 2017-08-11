package fr.socrates;

import fr.socrates.common.Printer;
import fr.socrates.domain.sponsor.Sponsor;
import fr.socrates.domain.sponsor.SponsorRespository;
import fr.socrates.domain.sponsor.SponsorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Yo Houssam, tu veux ajouter un candidat (1) ou les lister (2), un sponsor (3) ou les lister(4) ou un checkin (5) ou le nombre de repas froids (6) ? (0) si tu veux sortir !");
        String choice = scanner.next();
        switch (choice) {
            case "2":
                listerSponsors();
                break;
            case "3":
                createSponsor(scanner);
                break;

            default:
                System.out.println("N'importe quoi Houssam, concentre toi.");
        }
    }

    private static void listerSponsors() {

    }

    private static void createSponsor(Scanner scanner) {
        String[] sponsorInfos = {"Nom", "SIRET", "SIREN", "Représentant", "Contact Email", "Montant du sponsoring"};
        List<String> sponsorInputs = new ArrayList<>();

        for (String sponsorInfo : sponsorInfos) {
            System.out.println(sponsorInfo + ":");
            sponsorInputs.add(scanner.next());
        }
        FakePrinter printer = new FakePrinter();
        SponsorService sponsorService = new SponsorService(new FakeSponsorRespository(), printer);

        SponsorService listOfSponsors = new SponsorService(new FakeSponsorRespository(), new FakePrinter());
        listOfSponsors.add(new Sponsor.SponsorBuilder()
                .withName(sponsorInputs.get(0))
                .withSIRET(sponsorInputs.get(1))
                .withSIREN(sponsorInputs.get(2))
                .withContractRepresentative(sponsorInputs.get(3))
                .withContact(sponsorInputs.get(4))
                .withAmountOfSponsoring(Double.parseDouble(sponsorInputs.get(5)))
                .createSponsor());

    }

    private static class FakeSponsorRespository implements SponsorRespository {
        private final List<Sponsor> sponsors;

        public FakeSponsorRespository() {
            sponsors = new ArrayList<>();
        }

        public FakeSponsorRespository(List<Sponsor> sponsors) {

            this.sponsors = sponsors;
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
