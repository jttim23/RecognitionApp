package pl.jedro.recognitionApp.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GenderRecognitionServiceTests {
    @Test
    void shouldSplitNameToStrings(){
        GenderRecognitionService service = new GenderRecognitionService();
        String name = "Anna Jan Zbigniew";
        Assertions.assertEquals("FEMALE", service.determineGender(name));
    }
}
