package pl.jedro.recognitionApp.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jedro.recognitionApp.strategies.AllNamesAlgorithm;
import pl.jedro.recognitionApp.strategies.FirstNameAlgorithm;

import java.io.IOException;

public class GenderRecognitionServiceTests {
    private BasicGenderRecognitionService firstNameService;
    private BasicGenderRecognitionService allNamesService;

    @BeforeEach
    void setUp() {
        FirstNameAlgorithm firstNameAlgorithm = new FirstNameAlgorithm();
        AllNamesAlgorithm allNamesAlgorithm = new AllNamesAlgorithm();
        firstNameService = new BasicGenderRecognitionService(firstNameAlgorithm);
        allNamesService = new BasicGenderRecognitionService(allNamesAlgorithm);
    }
    @Test
    void throwExceptionWhenNameIsBlank() {
        String name="";
        Assertions.assertThrows(IllegalArgumentException.class,() -> firstNameService.determineGender(name));
        Assertions.assertThrows(IllegalArgumentException.class,() -> allNamesService.determineGender(name));
    }
    @Test
    void throwExceptionWhenNameIsOnlyWhiteSpaces() {
        String name="     ";
        Assertions.assertThrows(IllegalArgumentException.class,() -> firstNameService.determineGender(name));
        Assertions.assertThrows(IllegalArgumentException.class,() -> allNamesService.determineGender(name));

    }
    @Test
    void recognizeFemale() throws IOException {

        String name = " Anna  Jan   Zbigniew";
        Assertions.assertEquals("FEMALE", firstNameService.determineGender(name));

    }

    @Test
    void recognizeMale() throws IOException {
        String name = "Jan Jan Zbigniew";
        Assertions.assertEquals("MALE", firstNameService.determineGender(name));
    }

    @Test
    void recognizeInconclusive() throws IOException {
        String name = "Rokita Jan Zbigniew";
        Assertions.assertEquals("INCONCLUSIVE", firstNameService.determineGender(name));
    }
    @Test
    void recognizeFemaleByAllNames() throws IOException {

        String name = " Anna  Jan   Maria";
        Assertions.assertEquals("FEMALE", allNamesService.determineGender(name));

    }

    @Test
    void recognizeMaleByAllNames() throws IOException {
        String name = "Jan Maria Zbigniew";
        Assertions.assertEquals("MALE", allNamesService.determineGender(name));
    }

    @Test
    void recognizeInconclusiveByAllNames() throws IOException {
        String name = "Rokita Wójcik Imp";
        String secName = "Maria Jan";
        Assertions.assertEquals("INCONCLUSIVE", allNamesService.determineGender(name));
        Assertions.assertEquals("INCONCLUSIVE", allNamesService.determineGender(secName));
    }
}
