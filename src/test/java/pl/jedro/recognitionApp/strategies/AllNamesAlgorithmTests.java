package pl.jedro.recognitionApp.strategies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jedro.recognitionApp.model.Gender;

import java.io.IOException;
import java.util.ArrayList;

public class AllNamesAlgorithmTests {
    private AllNamesAlgorithm strategy;
    private ArrayList<String> names;

    @BeforeEach
    void setUp() {
        strategy = new AllNamesAlgorithm();
        names = new ArrayList<>();
    }

    @Test
    void responseMaleIfMoreMaleTokenMatches() throws IOException {
        names.add("Jan");
        Assertions.assertEquals(Gender.MALE, strategy.determineGender(names));
    }
    @Test
    void responseFemaleIfMoreFemaleTokenMatches() throws IOException {
        names.add("Maria");
        Assertions.assertEquals(Gender.FEMALE, strategy.determineGender(names));
    }
    @Test
    void responseInconclusiveIfNoTokensMatches() throws IOException {
        names.add("Rokita");
        Assertions.assertEquals(Gender.INCONCLUSIVE, strategy.determineGender(names));
    }
    @Test
    void responseInconclusiveWhenNumberOfTokensIsTheSame() throws IOException {
        names.add("Rokita");
        Assertions.assertEquals(Gender.INCONCLUSIVE, strategy.determineGender(names));
    }
}
