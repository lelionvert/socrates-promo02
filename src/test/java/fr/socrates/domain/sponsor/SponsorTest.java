package fr.socrates.sponsor;

import org.junit.Test;

public class SponsorTest {
    @Test(expected = IllegalStateException.class)
    public void should_not_allow_null_siren() throws Exception {
        new Sponsor.SponsorBuilder().withSIREN(null).createSponsor();
    }
}
