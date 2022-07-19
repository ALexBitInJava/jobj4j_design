package ru.job4j.io;

import java.io.*;

public class Analizy {
    private static boolean availability = true;
    public static void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter printer = new PrintWriter(new FileOutputStream(target))) {
            StringBuilder log = new StringBuilder();
            reader.lines().forEach(s -> checkFlag(log, s));
            printer.println(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkFlag(StringBuilder log, String line) {

        String[] array = line.split(" ");
        if (availability && ("400".equals(array[0]) || "500".equals(array[0]))) {
            log.append(array[1]).append(";");
            availability = false;
        }
        if (!availability && ("200".equals(array[0]) || "300".equals(array[0]))) {
            log.append(array[1]).append(";").append(System.lineSeparator());
            availability = true;
        }
    }

    public static void main(String[] args) throws IOException {
        unavailable("./data/server.log", "./data/unavailable.csv");
    }
}
