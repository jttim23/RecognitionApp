package pl.jedro.recognitionApp.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GenderTokensReaderTests {
    private FileReader femaleTokens;
    private FileReader emptyFemaleTokens;

    @BeforeEach
    void setUp() throws FileNotFoundException {

        femaleTokens = new FileReader("src/main/resources/static/femaleTokens.txt");
        emptyFemaleTokens = new FileReader("src/main/resources/static/emptyFemaleTokens.txt");

    }

//    @Test
//    void shouldThrowIOExceptionWhenFileEmpty() {
//        Assertions.assertThrows(IOException.class, () -> new GenderTokensReader(new BufferedReader(emptyFemaleTokens))
//                .getAllTokens());
//    }


//    @Test
//    void shouldReturnAllGenderTokens() throws IOException {
//        Assertions.assertEquals(3, new GenderTokensReader(new BufferedReader(femaleTokens
//        )).getAllTokens().size());
//    }
}
