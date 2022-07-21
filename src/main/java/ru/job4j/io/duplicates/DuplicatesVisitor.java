package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> mapFiles = new HashMap<>();

    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.toFile().getName());
        if (mapFiles.containsKey(fileProperty)) {
            List<Path> pathList1 = new ArrayList<>(mapFiles.get(fileProperty));
            pathList1.add(file);
            mapFiles.put(fileProperty, pathList1);
        } else {
            List<Path> pathList2 = new ArrayList<>();
            pathList2.add(file);
            mapFiles.put(fileProperty, pathList2);
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getDuplicates() {
        List<Path> duplicates = new ArrayList<>();
        mapFiles.values()
                .stream()
                .filter(paths -> paths.size() > 1)
                .forEach(duplicates::addAll);
        return duplicates;
    }
}
