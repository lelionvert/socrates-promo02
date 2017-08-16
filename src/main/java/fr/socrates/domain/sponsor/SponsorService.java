package fr.socrates.domain.sponsor;

import fr.socrates.common.Printer;

import java.util.List;

public class SponsorService {
    private final Printer printer;
    private final SponsorRespository sponsorRespository;

    public SponsorService(SponsorRespository sponsorRespository, Printer printer) {
        this.printer = printer;
        this.sponsorRespository = sponsorRespository;
    }

    public void addSponsor(Sponsor sponsor) {
        if (!this.sponsorRespository.getSponsorsList().contains(sponsor)) {
            this.sponsorRespository.addSponsor(sponsor);
        }
    }

    public List<Sponsor> getSponsorsList() {
        return this.sponsorRespository.getSponsorsList();
    }

    public void print() {
      for (Sponsor sponsor : getSponsorsList()) {
        sponsor.print(printer);
      }
    }
}
