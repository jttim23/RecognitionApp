package pl.jedro.recognitionApp.strategies;

import java.io.IOException;
import java.util.List;

public interface RecognitionStrategy {
    public String determineGender(List<String> names) throws IOException;
}
