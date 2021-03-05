package pl.jedro.recognitionApp.strategies;

import java.io.IOException;
import java.util.List;

public interface RecognitionStrategy {
     String determineGender(List<String> names) throws IOException;
}
