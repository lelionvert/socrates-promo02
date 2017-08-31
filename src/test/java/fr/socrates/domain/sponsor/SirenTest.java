package fr.socrates.domain.sponsor;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SirenTest {

    @Test(expected = NullPointerException.class)
    public void null_parameter_should_not_be_valid() throws Exception {
        new Siren(null);
    }

    @Test(expected = IllegalStateException.class)
    public void empty_string_should_not_be_a_valid_siret() throws Exception {
        new Siren("");
    }

    @Test(expected = IllegalStateException.class)
    public void siret_with_less_than_9_character_should_not_be_valid() throws Exception {
        new Siren("12345");
    }

    @Test(expected = IllegalStateException.class)
    public void siret_with_more_than_9_character_should_not_be_valid() throws Exception {
        new Siren("5132053445");
    }

    @Test
    public void spaces_should_not_interfere_with_siren_validation() throws Exception {
        assertThat(new Siren("513 205 344")).isNotNull();
    }

}