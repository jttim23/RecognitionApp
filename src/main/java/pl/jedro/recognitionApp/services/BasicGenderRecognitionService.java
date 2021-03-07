package pl.jedro.recognitionApp.services;

import pl.jedro.recognitionApp.strategies.RecognitionAlgorithm;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BasicGenderRecognitionService implements GenderRecognitionService {
    private RecognitionAlgorithm strategy;

    public BasicGenderRecognitionService(RecognitionAlgorithm strategy) {
        this.strategy = strategy;
    }

    public String determineGender(String fullName) throws IOException {
        return strategy.determineGender(getNames(fullName)).toString();
    }

    private List<String> getNames(String fullName) {
        if (fullName.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        return Arrays.asList(fullName.trim().split(" "));
    }
}
