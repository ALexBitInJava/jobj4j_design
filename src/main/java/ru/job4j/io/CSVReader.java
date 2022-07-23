package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        Path path = Paths.get(argsName.get("path"));
        Path target = Paths.get(argsName.get("out"));
        String delimiter = argsName.get("delimiter");

        List<String> filter = new ArrayList<>();
        try (var scanner = new Scanner(argsName.get("filter")).useDelimiter(",")) {
            while (scanner.hasNext()) {
                filter.add(scanner.next());
            }
        }

        List<String> columns = new ArrayList<>();
        try (var scanner = new Scanner(path)) {
            if (scanner.hasNextLine()) {
                String columnsLine = scanner.nextLine();
                var columnScanner = new Scanner(columnsLine).useDelimiter(delimiter);
                while (columnScanner.hasNext()) {
                    columns.add(columnScanner.next());
                }
            }
        }

        Map<String, List<String>> stringListMap = new HashMap<>();
        for (String c : columns) {
            stringListMap.put(c, new ArrayList<>());
        }
        int count = 0;
        try (var scanner = new Scanner(path).useDelimiter(delimiter)) {
            while (scanner.hasNext()) {
                count++;
                for (String f : filter) {
                    stringListMap.get(f).add(scanner.next());
                }
            }
        }

        StringBuilder rsl = new StringBuilder();
        for (int i = 0; i < count; i++) {
            List<String> strings = new ArrayList<>();
            for (String f : filter) {
                strings.add(stringListMap.get(f).get(i));
            }
            rsl.append(String.join(";", strings)).append("\n");
        }

        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(target.toFile()))) {
            out.write(rsl.toString().getBytes());
        }
    }

    private static String[] validateCsv(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("The root folder must have 4 parameters!");
        }
        Path path = Paths.get(ArgsName.of(args).get("path"));
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException("Not exist");
        }
        if (!path.toFile().isFile()) {
            throw new IllegalArgumentException("Not file");
        }
        return args;
    }

    public static void main(String[] args) throws Exception {
        handle(ArgsName.of(validateCsv(args)));
    }
}