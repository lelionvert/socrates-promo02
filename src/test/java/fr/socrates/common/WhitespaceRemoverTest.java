package fr.socrates.common;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WhitespaceRemoverTest {
    @Test
    public void empty_string_should_not_be_modified() throws Exception {
        assertThat(new WhitespaceRemover().apply("")).isEqualTo("");
    }

    @Test
    public void should_not_modify_string_without_spaces() throws Exception {
        assertThat(new WhitespaceRemover().apply("abc")).isEqualTo("abc");
    }

    @Test
    public void should_remove_whitespace_from_string() throws Exception {
        assertThat(new WhitespaceRemover().apply("a b c")).isEqualTo("abc");
    }
}