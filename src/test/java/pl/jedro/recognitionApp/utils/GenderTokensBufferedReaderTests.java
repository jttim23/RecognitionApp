package pl.jedro.recognitionApp.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;

@SpringBootTest(properties = {"males.path=src/test/java/resources/static/maleTokens.txt",
        "females.path=src/test/java/resources/static/femaleTokens.txt"})
public class GenderTokensBufferedReaderTests {
    @Autowired
    GenderTokensReader reader;


    @Test
    void getsFemaleStream() throws FileNotFoundException {
        Assertions.assertNotEquals(null, reader.getFemaleTokensStream());
    }

    @Test
    void getsMaleStream() throws FileNotFoundException {
        Assertions.assertNotEquals(null, reader.getMaleTokensStream());
    }
}