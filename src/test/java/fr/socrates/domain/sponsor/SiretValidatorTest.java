package fr.socrates.domain.sponsor;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SiretValidatorTest {

    private SiretValidator siretValidator;

    @Before
    public void setUp() throws Exception {
        siretValidator = new SiretValidator();
    }

    @Test
    public void null_parameter_should_not_be_valid() throws Exception {
        assertThat(siretValidator.isSiretSyntaxValide(null)).isFalse();
    }

    @Test
    public void empty_string_should_not_be_a_valid_siret() throws Exception {
        assertThat(siretValidator.isSiretSyntaxValide("")).isFalse();
    }

    @Test
    public void siret_with_less_than_14_character_should_not_be_valid() throws Exception {
        assertThat(siretValidator.isSiretSyntaxValide("12345")).isFalse();
    }

    @Test
    public void siret_with_more_than_14_character_should_not_be_valid() throws Exception {
        assertThat(siretValidator.isSiretSyntaxValide("513205344000310")).isFalse();
    }

    @Test
    public void spaces_should_not_interfere_with_sire_validation() throws Exception {
        assertThat(siretValidator.isSiretSyntaxValide("513 205 344 00031")).isTrue();
    }

    @Test
    public void should_recognize_valid_siret() throws Exception {
        assertThat(siretValidator.isSiretSyntaxValide("51320534400031")).isTrue();
        assertThat(siretValidator.isSiretSyntaxValide("51320534400032")).isFalse();
    }
}