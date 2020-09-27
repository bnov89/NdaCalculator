package org.bnov.nda.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileCollector {
    public List<File> getFiles(final String pathToFiles) throws IOException {
        if (pathToFiles == null) {
            throw new IllegalArgumentException("Path cannot be null");
        }
        try (Stream<Path> paths = Files.walk(Paths.get(pathToFiles))) {
            return paths.filter(Files::isRegularFile).map(Path::toFile).collect(Collectors.toList());
        }
    }
}
