package fr.socrates.domain.candidate;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EMailTest {
    @Test(expected = IllegalStateException.class)
    public void should_not_allow_an_invalid_email() throws Exception {
        EMail.of(null);
    }

    @Test
    public void should_be_a_value_type() throws Exception {
        EMail eMail1 = EMail.of("email1@mail.com");
        EMail eMail2 = EMail.of("email1@mail.com");
        assertThat(eMail1).isEqualTo(eMail2);
    }
}