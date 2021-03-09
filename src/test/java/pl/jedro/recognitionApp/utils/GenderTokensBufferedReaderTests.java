package pl.jedro.recognitionApp.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.jedro.recognitionApp.exceptions.PathNotSpecifiedException;

import java.io.FileNotFoundException;

@SpringBootTest(properties={"males.path=src/main/resources/static/maleTokens.txt",
        "females.path=src/main/resources/static/femaleTokens.txt"})
public class GenderTokensBufferedReaderTests {
@Autowired
GenderTokensReader reader;
    @Test
    void throwsPathNotSpecifiedException() {
        GenderTokensReader reader = new GenderTokensBufferedReader();
        Assertions.assertThrows(PathNotSpecifiedException.class, reader::getMaleTokensStream);
    }
    @Test
    void getsFemaleStream() throws FileNotFoundException {
        Assertions.assertNotEquals(null,reader.getFemaleTokensStream());
    }
    @Test
    void getsMaleStream() throws FileNotFoundException {
        Assertions.assertNotEquals(null,reader.getMaleTokensStream());
    }
}