package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("The method has no arguments");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .peek(s -> {
                    if (s.length() == 0) {
                        throw new IllegalArgumentException("There are no arguments");
                    }
                    if ((s.endsWith("=") && s.split("=").length == 1)
                            || !s.contains("=")
                            || !s.startsWith("-")
                            || s.startsWith("-=")) {
                        throw new IllegalArgumentException("The argument does not match the pattern \"key=value\"");
                    }
                })
                .filter(s -> s.contains("="))
                .map(s -> s.split("=", 2))
                .forEach(strings -> values.put(strings[0].substring(1), strings[1]));
    }

    public static ArgsName of(String[] args) {

        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
