package fr.socrates.sponsor;

import java.util.List;

/**
 * Created by lenovo_13 on 08/08/2017.
 */
class SponsorService {
    private final Printer printer;
    private final SponsorRespository sponsorRespository;

    public SponsorService(SponsorRespository sponsorRespository, Printer printer) {
        this.printer = printer;
        this.sponsorRespository = sponsorRespository;
    }

    public void addSponsor(Sponsor sponsor) {
        this.sponsorRespository.addSponsor(sponsor);
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
