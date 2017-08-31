package fr.socrates.domain.sponsor;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SirenTest {

    @Test(expected = NullPointerException.class)
    public void null_parameter_should_not_be_valid() throws Exception {
        Siren.of(null);
    }

    @Test(expected = IllegalStateException.class)
    public void empty_string_should_not_be_a_valid_siret() throws Exception {
        Siren.of("");
    }

    @Test(expected = IllegalStateException.class)
    public void siret_with_less_than_9_character_should_not_be_valid() throws Exception {
        Siren.of("12345");
    }

    @Test(expected = IllegalStateException.class)
    public void siret_with_more_than_9_character_should_not_be_valid() throws Exception {
        Siren.of("5132053445");
    }

    @Test
    public void spaces_should_not_interfere_with_siret_validation() throws Exception {
        assertThat(Siren.of("513 205 344")).isNotNull();
    }

    @Test
    public void should_recognize_valid_siret() throws Exception {
        assertThat(Siren.of("513205344")).isNotNull();
    }

    @Test(expected = IllegalStateException.class)
    public void should_recognize_invalid_siret() throws Exception {
        Siren.of("513205345");
    }

}