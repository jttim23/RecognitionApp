package pl.jedro.recognitionApp.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.jedro.recognitionApp.models.Genders;
import pl.jedro.recognitionApp.strategies.AlgorithmFactory;
import pl.jedro.recognitionApp.strategies.AlgorithmNames;
import pl.jedro.recognitionApp.utils.GenderTokensReader;

import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootTest(properties = {"males.path=src/test/java/resources/static/maleTokens.txt",
        "females.path=src/test/java/resources/static/femaleTokens.txt"})
public class GenderRecognitionServiceTests {
    @Autowired
    GenderTokensReader readerV2;
    @Autowired
    private AlgorithmFactory algorithmFactory;
    private BasicGenderRecognitionService firstNameService;
    private BasicGenderRecognitionService allNamesService;

    @BeforeEach
    void setUp() {
        firstNameService = new BasicGenderRecognitionService(readerV2);
        firstNameService.setAlgorithm(algorithmFactory.findAlgorithm(AlgorithmNames.FIRST_NAME_ALGORITHM));
        allNamesService = new BasicGenderRecognitionService(readerV2);
        allNamesService.setAlgorithm(algorithmFactory.findAlgorithm(AlgorithmNames.ALL_NAMES_ALGORITHM));
    }

    @Test
    void responsesWithListOfAllMaleTokens() throws FileNotFoundException {
        Assertions.assertEquals(3, firstNameService.getListOfTokens("male").size());
    }

    @Test
    void responsesWithListOfAllFemaleTokens() throws FileNotFoundException {
        Assertions.assertEquals(3, allNamesService.getListOfTokens("female").size());
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
        Assertions.assertEquals(Genders.FEMALE, firstNameService.determineGender(name));

    }

    @Test
    void recognizeMaleByFirstName() throws IOException {
        String name = "Jan Jan Zbigniew";
        Assertions.assertEquals(Genders.MALE, firstNameService.determineGender(name));
    }

    @Test
    void recognizeInconclusiveByFirstName() throws IOException {
        String name = "Rokita Jan Zbigniew";
        Assertions.assertEquals(Genders.INCONCLUSIVE, firstNameService.determineGender(name));
    }

    @Test
    void recognizeFemaleByAllNames() throws IOException {

        String name = " Anna  Jan   Maria";

        Assertions.assertEquals(Genders.FEMALE, allNamesService.determineGender(name));

    }

    @Test
    void recognizeMaleByAllNames() throws IOException {
        String name = "Jan Maria Zbigniew";

        Assertions.assertEquals(Genders.MALE, allNamesService.determineGender(name));
    }

    @Test
    void recognizeInconclusiveByAllNames() throws IOException {
        String secName = "jan Maria";

        //Assertions.assertEquals(Gender.INCONCLUSIVE, allNamesService.determineGender(name));
        Assertions.assertEquals(Genders.INCONCLUSIVE, allNamesService.determineGender(secName));
    }
}
