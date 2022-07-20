package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
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

    @Test(expected = IllegalArgumentException.class)
    public void whenTheRightAndLeftAreEmpty() {
        String path = "./data/whenTheRightAreEmpty.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTheLeftAndLeftAreEmpty() {
        String path = "./data/whenTheLeftAreEmpty.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTheLeftAndRightAreEmpty() {
        String path = "./data/whenTheLeftAndRightAreEmpty.properties";
        Config config = new Config(path);
        config.load();
    }
}