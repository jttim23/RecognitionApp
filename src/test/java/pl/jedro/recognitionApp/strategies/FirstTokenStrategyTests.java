package pl.jedro.recognitionApp.strategies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FirstTokenStrategyTests {

    @Test
    void shouldResponseInconclusiveWhenNoTokensMatching(){
        FirstTokenStrategy strategy = new FirstTokenStrategy();
        Assertions.assertEquals("INCONCLUSIVE", strategy.getResponse());
    }
}
