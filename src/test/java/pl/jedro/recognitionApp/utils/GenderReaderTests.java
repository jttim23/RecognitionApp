package pl.jedro.recognitionApp.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GenderReaderTests {
    private GenderReader reader;
    private FileReader femaleTokens;
    private FileReader emptyFemaleTokens;
    @BeforeEach
    void setUp() throws FileNotFoundException {
         reader = new GenderReader();
         femaleTokens = new FileReader( "src/main/resources/static/femaleTokens.txt");
         emptyFemaleTokens = new FileReader( "src/main/resources/static/emptyFemaleTokens.txt");
    }

    @Test
    void shouldThrowIOExceptionWhenFileEmpty() {
        Assertions.assertThrows(IOException.class, () -> reader.getGenderToken(emptyFemaleTokens));
    }
    @Test
    void shouldReturnAtLeastOneGenderToken() throws IOException {
        Assertions.assertNotEquals(null,reader.getGenderToken(femaleTokens));
    }
    @Test
    void shouldReturnAllGenderTokens() throws IOException {
        Assertions.assertEquals(3, reader.getAllTokens(femaleTokens).size());
    }
}
