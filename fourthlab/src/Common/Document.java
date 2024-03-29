package Common;

import java.io.File;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.io.BufferedReader;

public class Document {
    private final List<String> lines;
    private final String name;

    public Document(List<String> lines, String name) {
        this.lines = lines;
        this.name = name;
    }

    public List<String> getLines() {
        return this.lines;
    }

    public String getName() {
        return name;
    }

    public static Document fromFile(File file) throws IOException {
        List<String> lines = new LinkedList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        }
        return new Document(lines, file.getName());
    }
}
