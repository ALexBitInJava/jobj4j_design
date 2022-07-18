package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ResultFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 0; i < size; i++) {
                for (int g = 0; g < size; g++) {
                    out.write(((i + 1) * (g + 1) + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
