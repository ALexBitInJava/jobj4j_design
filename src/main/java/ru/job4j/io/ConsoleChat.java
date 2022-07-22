package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean abilityToSpeak = true;
        List<String> logDialog = new ArrayList<>();
        String userPhrase = "";
        while (!OUT.equalsIgnoreCase(userPhrase)) {
            System.out.println("Слово за тобой, парень: ");
            userPhrase = scanner.nextLine();
            logDialog.add(userPhrase);
            if (userPhrase.isBlank() || OUT.equalsIgnoreCase(userPhrase)) {
                continue;
            } else if (STOP.equalsIgnoreCase(userPhrase)) {
                abilityToSpeak = false;
                continue;
            } else if (CONTINUE.equalsIgnoreCase(userPhrase)) {
                abilityToSpeak = true;
            }

            if (abilityToSpeak) {
                String phraseGeneration = readPhrases().get((int) (Math.random() * readPhrases().size()));
                logDialog.add(phraseGeneration);
                System.out.println("А бот ему в ответ: " + phraseGeneration);
            }
        }
        saveLog(logDialog);
    }

    private List<String> readPhrases() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            answers.addAll(br.lines().toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\projects\\job4j_design\\src\\data/chatLog.txt", "C:\\projects\\job4j_design\\src\\data/botAnswers.txt");
        cc.run();
    }
}