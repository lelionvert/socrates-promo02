package fr.socrates.domain.sponsor;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SponsorTest {
    @Test(expected = IllegalStateException.class)
    public void should_not_allow_invalid_siren() throws Exception {
        new Sponsor.SponsorBuilder().withSIREN("");
    }

    @Test(expected = IllegalStateException.class)
    public void should_not_allow_invalid_siret() throws Exception {
        new Sponsor.SponsorBuilder().withSIRET("");
    }

    @Test
    public void siren_should_be_extracted_from_siret() throws Exception {
        final Sponsor sponsorWithSiret = new Sponsor.SponsorBuilder().withSIRET("51320534400031").createSponsor();
        final Sponsor sponsorWithSiren = new Sponsor.SponsorBuilder().withSIREN("513205344").createSponsor();
        assertThat(sponsorWithSiret).isEqualTo(sponsorWithSiren);
    }
}
