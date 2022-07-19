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
                    .filter(s -> {
                        if (!s.startsWith("#") && s.contains("=")) {
                            throw new IllegalArgumentException();
                        }
                        return true;
                    })
                    .filter(s -> {
                        if (!s.startsWith("=") && (s.lastIndexOf("=") != (s.length() - 1))) {
                            throw new IllegalArgumentException("");
                        }
                        return true;
                    })
                    .forEach(s -> {
                        String[] strings = s.split("=", 2);
                        values.put(strings[0], strings[1]);
                    });
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
        Config config = new Config("app.properties");
        config.load();
        System.out.println(config.values);
    }
}
