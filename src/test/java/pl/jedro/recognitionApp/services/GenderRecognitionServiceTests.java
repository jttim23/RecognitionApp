package pl.jedro.recognitionApp.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jedro.recognitionApp.controllers.MainController;
import pl.jedro.recognitionApp.model.Gender;
import pl.jedro.recognitionApp.strategies.AllNamesAlgorithm;
import pl.jedro.recognitionApp.strategies.FirstNameAlgorithm;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GenderRecognitionServiceTests {
    private BasicGenderRecognitionService firstNameService;
    private BasicGenderRecognitionService allNamesService;

    @BeforeEach
    void setUp() {
        firstNameService = new BasicGenderRecognitionService();
        firstNameService.setAlgorithm(new FirstNameAlgorithm());
        allNamesService = new BasicGenderRecognitionService();
        allNamesService.setAlgorithm(new AllNamesAlgorithm());
    }
    @Test
    void responsesWithListOfAllMaleTokens() throws FileNotFoundException {
        Assertions.assertEquals(3, firstNameService.getListOfMaleTokens().size());
    }
    @Test
    void responsesWithListOfAllFemaleTokens() throws FileNotFoundException {
        Assertions.assertEquals(3, allNamesService.getListOfFemaleTokens().size());
    }
    @Test
    void throwExceptionWhenNameIsBlank() {
        String name = "";
        Assertions.assertThrows(IllegalArgumentException.class, () -> firstNameService.determineGender(name));
        Assertions.assertThrows(IllegalArgumentException.class, () -> allNamesService.determineGender(name));
    }

    @Test
    void throwExceptionWhenNameIsOnlyWhiteSpaces() {
        String name = "     ";
        Assertions.assertThrows(IllegalArgumentException.class, () -> firstNameService.determineGender(name));
        Assertions.assertThrows(IllegalArgumentException.class, () -> allNamesService.determineGender(name));

    }

    @Test
    void recognizeFemaleByFirstName() throws IOException {

        String name = " Anna  Jan   Zbigniew";
        Assertions.assertEquals(Gender.FEMALE, firstNameService.determineGender(name));

    }

    @Test
    void recognizeMaleByFirstName() throws IOException {
        String name = "Jan Jan Zbigniew";
        Assertions.assertEquals(Gender.MALE, firstNameService.determineGender(name));
    }

    @Test
    void recognizeInconclusiveByFirstName() throws IOException {
        String name = "Rokita Jan Zbigniew";
        Assertions.assertEquals(Gender.INCONCLUSIVE, firstNameService.determineGender(name));
    }

    @Test
    void recognizeFemaleByAllNames() throws IOException {

        String name = " Anna  Jan   Maria";

        Assertions.assertEquals(Gender.FEMALE, allNamesService.determineGender(name));

    }

    @Test
    void recognizeMaleByAllNames() throws IOException {
        String name = "Jan Maria Zbigniew";

        Assertions.assertEquals(Gender.MALE, allNamesService.determineGender(name));
    }

    @Test
    void recognizeInconclusiveByAllNames() throws IOException {
        String name = "Rokita Wójcik Imp";
        String secName = "Maria Jan";

        Assertions.assertEquals(Gender.INCONCLUSIVE, allNamesService.determineGender(name));
        Assertions.assertEquals(Gender.INCONCLUSIVE, allNamesService.determineGender(secName));
    }
}
