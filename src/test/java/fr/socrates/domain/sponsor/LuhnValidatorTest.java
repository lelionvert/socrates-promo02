package fr.socrates.domain.sponsor;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LuhnValidatorTest {
    @Test
    public void should_recognize_valid_siret() throws Exception {
        assertThat(LuhnValidator.SIRET.test("51320534400031")).isTrue();
    }

    @Test
    public void should_recognize_valid_siren() throws Exception {
        assertThat(LuhnValidator.SIREN.test("513205344")).isTrue();
    }

    @Test
    public void should_recognize_invalid_siren() throws Exception {
        assertThat(LuhnValidator.SIREN.test("513205345")).isFalse();
    }

    @Test
    public void should_recognize_invalid_siret() throws Exception {
        assertThat(LuhnValidator.SIRET.test("51320534400032")).isFalse();
    }

}