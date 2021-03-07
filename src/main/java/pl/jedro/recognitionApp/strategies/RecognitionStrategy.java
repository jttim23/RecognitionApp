package pl.jedro.recognitionApp.strategies;

import pl.jedro.recognitionApp.model.Gender;

import java.io.IOException;
import java.util.List;

public interface RecognitionStrategy {
     Gender determineGender(List<String> names) throws IOException;
}
