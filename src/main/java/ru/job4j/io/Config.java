package ru.job4j.io;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(s -> !s.isEmpty() && !s.startsWith("#"))
                    .filter(s -> {
                        if (s.contains("=")) {
                            throw new IllegalArgumentException("The string does not contain the \"=\" sign");
                        }
                        if (!s.startsWith("=")) {
                            throw new IllegalArgumentException("The string starts with \"=\" sign");
                        }
                        return true;
                    })
                    .map(s -> s.split("=", 2))
                    .filter(s -> {
                        if (s[0].isEmpty() || s[1].isEmpty()) {
                            throw new IllegalArgumentException("The first occurrence of the \"=\" character is the last character in the string");
                        }
                        return true;
                    })
                    .forEach(strings -> values.put(strings[0], strings[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
