package fr.socrates.domain.inseeCode;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LuhnValidatorTest {
    private LuhnValidator luhnValidator;

    @Before
    public void setUp() throws Exception {
        luhnValidator = new LuhnValidator();
    }

    @Test
    public void should_recognize_valid_siret() throws Exception {
        assertThat(luhnValidator.test("51320534400031")).isTrue();
    }

    @Test
    public void should_recognize_valid_siren() throws Exception {
        assertThat(luhnValidator.test("513205344")).isTrue();
    }

    @Test
    public void should_recognize_invalid_siren() throws Exception {
        assertThat(luhnValidator.test("513205345")).isFalse();
    }

    @Test
    public void should_recognize_invalid_siret() throws Exception {
        assertThat(luhnValidator.test("51320534400032")).isFalse();
    }

}