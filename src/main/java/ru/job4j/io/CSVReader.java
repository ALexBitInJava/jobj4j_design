package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        Path path = Paths.get(argsName.get("path"));
        String delimiter = argsName.get("delimiter");

        List<String> listFilter = List.of(argsName.get("filter").split(","));

        List<Integer> listLine = new ArrayList<>();
        try (var scanner = new Scanner(path)) {
            List<String> firstLine = List.of(scanner.nextLine().split(delimiter));
            for (int i = 0; i < firstLine.size(); i++) {
                if (listFilter.contains(firstLine.get(i))) {
                    listLine.add(i);
                }
            }
        }

        String data = "";
        try (var scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                String[] lineData = scanner.nextLine().split(delimiter);
                for (int i = 0; i < listLine.size(); i++) {
                    if (i < listLine.size() - 1) {
                        data = data.concat(lineData[listLine.get(i)]).concat(delimiter);
                    } else if (i == listLine.size() - 1) {
                        data = data.concat(lineData[listLine.get(i)]).concat(System.lineSeparator());
                    }
                }
            }
        }


        if ("stdout".equals(argsName.get("out"))) {
            System.out.println(data);
        } else {
            try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(argsName.get("out")))) {
                out.write(data.getBytes(StandardCharsets.UTF_8));
            }
        }

    }

    private static void validateCsv(String[] args, ArgsName argsName) {
        if (args.length != 4) {
            throw new IllegalArgumentException("The root folder must have 4 parameters!");
        }
        File path = Paths.get(argsName.get("path")).toFile();
        if (!path.exists()) {
            throw new IllegalArgumentException(String.format("The parameter 'path' = %s doesn't exist", argsName.get("path")));
        }
        if (argsName.get("filter").isEmpty()) {
            throw new IllegalArgumentException("Filter doesn't exist and must contain 1 parameter");
        }
        if (!";".equals(argsName.get("delimiter"))) {
            throw new IllegalArgumentException(String.format("Delimiter %s should be ',' ", argsName.get("delimiter")));
        }
        if (!new File(argsName.get("out")).isFile()) {
            throw new IllegalArgumentException(String.format("Out %s should be 'stdout' ", argsName.get("out")));
        }
    }

    public static void main(String[] args) throws Exception {
        handle(ArgsName.of(args));
        validateCsv(args, ArgsName.of(args));
    }
}