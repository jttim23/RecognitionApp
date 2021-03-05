package pl.jedro.recognitionApp.services;

import pl.jedro.recognitionApp.strategies.RecognitionStrategy;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GenderRecognitionService {
    private RecognitionStrategy strategy;

    public GenderRecognitionService(RecognitionStrategy strategy) {
        this.strategy = strategy;
    }

    public String determineGender(String fullName) throws IOException {
        List<String> names = Arrays.asList(fullName.trim().split(" "));
        return strategy.determineGender(names);
    }
}
