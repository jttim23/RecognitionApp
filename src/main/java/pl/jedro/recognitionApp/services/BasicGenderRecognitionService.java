package pl.jedro.recognitionApp.services;

import pl.jedro.recognitionApp.model.GenderToken;
import pl.jedro.recognitionApp.strategies.RecognitionAlgorithm;
import pl.jedro.recognitionApp.utils.GenderTokensBufferedReader;
import pl.jedro.recognitionApp.utils.GenderTokensReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BasicGenderRecognitionService implements GenderRecognitionService {
    private RecognitionAlgorithm strategy;

    public BasicGenderRecognitionService(RecognitionAlgorithm strategy) {
        this.strategy = strategy;
    }

    public String determineGender(String fullName) throws IOException {
        return strategy.determineGender(getNames(fullName)).toString();
    }

    @Override
    public List<GenderToken> getListOfMaleTokens() throws FileNotFoundException {
        GenderTokensReader reader = new GenderTokensBufferedReader(new FileReader("src/main/resources/static/maleTokens.txt"));
        return reader.getStreamTokens().collect(Collectors.toList());
    }

    private List<String> getNames(String fullName) {
        if (fullName.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        return Arrays.asList(fullName.trim().split(" "));
    }
}
