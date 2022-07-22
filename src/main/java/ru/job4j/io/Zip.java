package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target)))) {
            for (Path path : sources) {
                zipOutputStream.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zipOutputStream.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void zipValidation(ArgsName argsName) {
        argsName.get("o");
        File file = new File(argsName.get("d"));
        if (!file.exists()) {
            throw new IllegalArgumentException("The file does not exist %s " + file.getAbsolutePath());
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("The directory does not exist %s " + file.isDirectory());
        }
        if (!argsName.get("e").startsWith(".")) {
            throw new IllegalArgumentException("Does not start with '.' %s" + argsName.get("e"));
        }
    }

    public void packSingleFile(List<Path> source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : source) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
            ArgsName argsName = ArgsName.of(args);
            zipValidation(argsName);
            new Zip().packFiles(Search.search(Path.of(argsName.get("d")),
                    path -> !path.toFile().getName().
                            endsWith(argsName.get("e"))),
                    new File(argsName.get("o")));
    }
}