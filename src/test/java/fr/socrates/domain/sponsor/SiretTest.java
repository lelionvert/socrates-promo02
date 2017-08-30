package fr.socrates.domain.sponsor;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SiretTest {

    @Test(expected = IllegalStateException.class)
    public void null_parameter_should_not_be_valid() throws Exception {
        Siret.of(null);
    }

    @Test(expected = IllegalStateException.class)
    public void empty_string_should_not_be_a_valid_siret() throws Exception {
        Siret.of("");
    }

    @Test(expected = IllegalStateException.class)
    public void siret_with_less_than_14_character_should_not_be_valid() throws Exception {
        Siret.of("12345");
    }

    @Test(expected = IllegalStateException.class)
    public void siret_with_more_than_14_character_should_not_be_valid() throws Exception {
        Siret.of("513205344000310");
    }

    @Test
    public void spaces_should_not_interfere_with_siret_validation() throws Exception {
        assertThat(Siret.of("513 205 344 00031")).isNotNull();
    }

    @Test
    public void should_recognize_valid_siret() throws Exception {
        assertThat(Siret.of("51320534400031")).isNotNull();
    }

    @Test(expected = IllegalStateException.class)
    public void should_recognize_invalid_siret() throws Exception {
        Siret.of("51320534400032");
    }
}