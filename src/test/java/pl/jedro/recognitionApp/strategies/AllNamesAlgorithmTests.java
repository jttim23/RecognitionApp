package pl.jedro.recognitionApp.strategies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.jedro.recognitionApp.models.Genders;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootTest(properties = {"males.path=src/main/resources/static/maleTokens.txt",
        "females.path=src/main/resources/static/femaleTokens.txt"})
public class AllNamesAlgorithmTests {
    @Autowired
    private AllNamesAlgorithm strategy;
    private ArrayList<String> names;

    @BeforeEach
    void setUp() {
        names = new ArrayList<>();
    }

    @Test
    void responseMaleIfMoreMaleTokenMatches() throws IOException {
        names.add("jan");
        Assertions.assertEquals(Genders.MALE, strategy.determineGender(names));
    }

    @Test
    void responseFemaleIfMoreFemaleTokenMatches() throws IOException {
        names.add("maria");
        Assertions.assertEquals(Genders.FEMALE, strategy.determineGender(names));
    }

    @Test
    void responseInconclusiveIfNoTokensMatches() throws IOException {
        names.add("rokita");
        Assertions.assertEquals(Genders.INCONCLUSIVE, strategy.determineGender(names));
    }


}
