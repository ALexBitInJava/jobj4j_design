package searchforfilesbycriteria;

import ru.job4j.io.SearchFiles;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static Path validateSearch(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("The entered arguments must contain 4 parameters. Example of filling in: 'java -jar find.jar -d=c:/ -n=*.?xt -t=mask -o=log.txt'");
        }

        Path path = Paths.get(ArgsName.of(args).get("d"));
        String stringSearch = ArgsName.of(args).get("t");

        if (!"name".equals(stringSearch) && !"mask".equals(stringSearch) && !"regex".equals(stringSearch)) {
            throw new IllegalArgumentException("Type \"t\" can accept parameters such as \"mask\", \"name\" and \"regex\"");
        }
        if (!path.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("%s does not exist or is specified incorrectly", path.toFile().getAbsolutePath()));
        }
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException(String.format("%s does not exist", path.toFile().getAbsoluteFile()));
        }
        return Path.of(ArgsName.of(args).get("d"));
    }

    public static Predicate<Path> predicate(ArgsName argsName) {
        Predicate<Path> predicate = null;
        if ("name".equals(argsName.get("t"))) {
            predicate = path -> path.toFile().getName().equals(argsName.get("n"));
        } else if ("mask".equals(argsName.get("t"))) {
            predicate = path -> path.toFile().getName().matches(argsName.get("n").replace(".", "[.]")
                    .replace("*", ".+").replace("?", "."));
        } else if ("regex".equals(argsName.get("t"))) {
            predicate = path -> path.toFile().getName().matches(argsName.get("n"));
        }
        return predicate;
    }

    public static void writeResult(List<Path> pathList, String file) {
        try (PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (Path path : pathList) {
                pw.write(String.valueOf(path));
                pw.write(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searchFiles = new SearchFiles(condition);
        Files.walkFileTree(root, searchFiles);
        return searchFiles.getPaths();
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        validateSearch(args);
        Predicate<Path> predicate = predicate(argsName);
        List<Path> list = search(Path.of(argsName.get("d")), predicate);
        writeResult(list, argsName.get("o"));
    }
}
