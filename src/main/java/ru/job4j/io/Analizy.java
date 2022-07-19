package ru.job4j.io;

import java.io.*;

public class Analizy {
    public static void unavailable(String source, String target) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            StringBuilder log = new StringBuilder();
            reader.lines().forEach(s -> checkFlag(log, s));
            out.println(log);
        }
    }
    public static void checkFlag(StringBuilder log, String line) {
        String[] array = line.split(" ");
        if ("400".equals(array[0]) || "500".equals(array[0])) {
            log.append(array[1]).append(";");
        }
    }
    public static void main(String[] args) throws IOException {
        unavailable("./data/server.log", "./data/unavailable.csv");
    }
}
