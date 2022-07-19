package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenReadingTwoValues() {
        String path = "./data/whenReadingTwoValues.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Alexandr"));
        assertThat(config.value("surname"), is("Golovin"));
    }

    @Test
    public void whenReadingComment() {
        String path = "./data/whenReadingCommentAndEmptyLines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Alexandr"));
        assertThat(config.value("surname"), is("Golovin"));
    }
}