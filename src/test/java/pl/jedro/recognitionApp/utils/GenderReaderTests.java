package pl.jedro.recognitionApp.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class GenderReaderTests {
    @Test
    void shouldThrowIOExceptionWhenFileEmpty(){
        GenderReader reader = new GenderReader();
        Assertions.assertThrows(IOException.class, reader::getOneToken);
    }
}
