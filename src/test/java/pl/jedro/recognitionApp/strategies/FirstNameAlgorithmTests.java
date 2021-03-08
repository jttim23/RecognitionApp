package pl.jedro.recognitionApp.strategies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jedro.recognitionApp.model.Gender;

import java.io.IOException;
import java.util.ArrayList;

public class FirstNameAlgorithmTests {
    private FirstNameAlgorithm strategy;
    private ArrayList<String> names;

    @BeforeEach
    void setUp() {
        strategy = new FirstNameAlgorithm("src/main/resources/static/maleTokens.txt","src/main/resources/static/femaleTokens.txt");
        names = new ArrayList<>();
    }
    @Test
    void responseInconclusiveWhenNoTokensMatchesFirstName() throws IOException {
        names.add("Rokita");
        Assertions.assertEquals(Gender.INCONCLUSIVE, strategy.determineGender(names));
    }

    @Test
    void responseMaleIfMaleTokenMatchesOnlyFirstName() throws IOException {
        names.add("Jan");
        names.add("rokita");
        Assertions.assertEquals(Gender.MALE, strategy.determineGender(names));
    }
    @Test
    void responseMaleIfMaleTokenMatchesFirstName() throws IOException {

        names.add("Jan");
        Assertions.assertEquals(Gender.MALE, strategy.determineGender(names));
    }
    @Test
    void responseCorrectlyWhenNameInLowerCase() throws IOException {
        names.add("anna");
        Assertions.assertEquals(Gender.FEMALE, strategy.determineGender(names));
    }

    @Test
    void responseFemaleIfFemaleTokenMatchesFirstName() throws IOException {
        names.add("Maria");
        Assertions.assertEquals(Gender.FEMALE, strategy.determineGender(names));
    }
}

