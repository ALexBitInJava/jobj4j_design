package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                    text.append((char) read);
            }
            String[] array = text.toString().split(System.lineSeparator());
            for (String a : array) {
                if (Integer.parseInt(a) % 2 == 0) {
                    System.out.println(a);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
