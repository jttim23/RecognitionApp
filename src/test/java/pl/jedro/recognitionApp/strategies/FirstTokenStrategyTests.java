package pl.jedro.recognitionApp.strategies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FirstTokenStrategyTests {

    @Test
    void shouldResponseInconclusiveWhenNoTokensMatches() throws IOException {
        FirstTokenStrategy strategy = new FirstTokenStrategy();
        Assertions.assertEquals("INCONCLUSIVE", strategy.getResponse(""));
    }
    @Test
    void shouldResponseMaleIfMaleTokenMatches() throws IOException {
        FirstTokenStrategy strategy = new FirstTokenStrategy();
        String name = "Jan";
        Assertions.assertEquals("MALE", strategy.getResponse(name));
    }
    @Test
    void shouldResponseFemaleIfFemaleTokenMatches() throws IOException {
        FirstTokenStrategy strategy = new FirstTokenStrategy();
        String name = "Maria";
        Assertions.assertEquals("FEMALE", strategy.getResponse(name));
    }
}

