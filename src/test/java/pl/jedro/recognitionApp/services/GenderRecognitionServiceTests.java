package pl.jedro.recognitionApp.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jedro.recognitionApp.strategies.FirstTokenStrategy;

import java.io.IOException;

public class GenderRecognitionServiceTests {
    private GenderRecognitionService service;

    @BeforeEach
    void setUp() {
        FirstTokenStrategy strategy = new FirstTokenStrategy();
        service = new GenderRecognitionService(strategy);
    }

    @Test
    void shouldRecognizeFemale() throws IOException {

        String name = " Anna Jan    Zbigniew";
        Assertions.assertEquals("FEMALE", service.determineGender(name));
    }

    @Test
    void shouldRecognizeMale() throws IOException {
        String name = "Jan Jan Zbigniew";
        Assertions.assertEquals("MALE", service.determineGender(name));
    }

    @Test
    void shouldRecognizeInconclusive() throws IOException {
        String name = "Rokita Jan Zbigniew";
        Assertions.assertEquals("INCONCLUSIVE", service.determineGender(name));
    }
}
