package fr.socrates.domain.inseeCode;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SiretTest {

    @Test(expected = NullPointerException.class)
    public void null_parameter_should_not_be_valid() throws Exception {
        new Siret(null);
    }

    @Test(expected = IllegalStateException.class)
    public void empty_string_should_not_be_a_valid_siret() throws Exception {
        new Siret("");
    }

    @Test(expected = IllegalStateException.class)
    public void siret_with_less_than_14_character_should_not_be_valid() throws Exception {
        new Siret("12345");
    }

    @Test(expected = IllegalStateException.class)
    public void siret_with_more_than_14_character_should_not_be_valid() throws Exception {
        new Siret("513205344000310");
    }

    @Test
    public void spaces_should_not_interfere_with_siret_validation() throws Exception {
        assertThat(new Siret("513 205 344 00031")).isNotNull();
    }

    @Test
    public void should_recognize_valid_siret() throws Exception {
        assertThat(new Siret("51320534400031")).isNotNull();
    }

    @Test(expected = IllegalStateException.class)
    public void should_recognize_invalid_siret() throws Exception {
        new Siret("51320534400032");
    }
}