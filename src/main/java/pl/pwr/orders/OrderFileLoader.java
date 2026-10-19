package pl.pwr.orders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class OrderFileLoader {

    private final Path directory; //pole ścieżka do katalogu

    public OrderFileLoader(String directoryPath) {
        this.directory = Path.of(directoryPath); //zamiana tekstu na obiekt
    }

    public List<Path> loadJsonFiles() throws IOException {
        try (Stream<Path> files = Files.list(directory)) {
            return files
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().endsWith(".json"))
                    .sorted()
                    .toList();
        }
    }
}

//Files- klasa z narzędziami do plikow i katalogow
//.list -odp na pytanie co jest w środku katalogu??