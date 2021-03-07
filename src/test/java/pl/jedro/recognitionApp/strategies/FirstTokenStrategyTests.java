package pl.jedro.recognitionApp.strategies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jedro.recognitionApp.model.Gender;

import java.io.IOException;
import java.util.ArrayList;

public class FirstTokenStrategyTests {
    private FirstTokenStrategy strategy;
    private ArrayList<String> names;

    @BeforeEach
    void setUp() {
        strategy = new FirstTokenStrategy();
        names = new ArrayList<>();
    }
    @Test
    void shouldResponseInconclusiveWhenNoTokensMatches() throws IOException {
        names.add("Rokita");
        Assertions.assertEquals(Gender.INCONCLUSIVE, strategy.determineGender(names));
    }

    @Test
    void shouldResponseMaleIfMaleTokenMatches() throws IOException {

        names.add("Jan");
        Assertions.assertEquals(Gender.MALE, strategy.determineGender(names));
    }

    @Test
    void shouldResponseFemaleIfFemaleTokenMatches() throws IOException {
        names.add("Maria");
        Assertions.assertEquals(Gender.FEMALE, strategy.determineGender(names));
    }
}

