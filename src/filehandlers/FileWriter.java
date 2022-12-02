package filehandlers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileWriter {
    public void write(String file, List<String> lines) {
        Path path = Path.of(file);
        try {
            Files.write(path, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void append(String file, List<String> lines) {
        Path path = Path.of(file);
        try {
            Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
