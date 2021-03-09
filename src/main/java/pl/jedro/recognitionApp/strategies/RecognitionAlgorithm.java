package pl.jedro.recognitionApp.strategies;

import pl.jedro.recognitionApp.model.Genders;

import java.io.IOException;
import java.util.List;

public interface RecognitionAlgorithm {
    Genders determineGender(List<String> names) throws IOException;

    AlgorithmNames getAlgorithmName();
}
