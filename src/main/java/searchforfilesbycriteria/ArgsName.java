package searchforfilesbycriteria;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();
    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("The array of Args is empty");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .filter(s -> {
                    if ((!s.contains("=")
                            || (s.indexOf("=") == s.length() - 1)
                            || !s.startsWith("-")
                            || s.startsWith("-="))) {
                        throw new IllegalArgumentException("The argument does not match the pattern \"key=value\"");
                    }
                    return true;
                })
                .map(s -> s.split("="))
                .forEach(strings -> values.put(strings[0].substring(1), strings[1]));
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("The array of Args is empty");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ru.job4j.io.ArgsName jvm = ru.job4j.io.ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ru.job4j.io.ArgsName zip = ru.job4j.io.ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
