package pl.jedro.recognitionApp.services;

import org.springframework.stereotype.Service;
import pl.jedro.recognitionApp.model.Gender;
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

@Service
public class BasicGenderRecognitionService implements GenderRecognitionService {

    private RecognitionAlgorithm algorithm;

    @Override
    public void setAlgorithm(RecognitionAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public Gender determineGender(String fullName) throws IOException {
        return algorithm.determineGender(splitNameToArray(fullName));
    }

    @Override
    public List<GenderToken> getListOfMaleTokens() throws FileNotFoundException {
        GenderTokensReader reader = new GenderTokensBufferedReader(new FileReader("src/main/resources/static/maleTokens.txt"));
        return reader.getStreamTokens().collect(Collectors.toList());
    }

    @Override
    public List<GenderToken> getListOfFemaleTokens() throws FileNotFoundException {
        GenderTokensReader reader = new GenderTokensBufferedReader(new FileReader("src/main/resources/static/femaleTokens.txt"));
        return reader.getStreamTokens().collect(Collectors.toList());
    }

    private List<String> splitNameToArray(String fullName) {
        if (fullName.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        return Arrays.asList(fullName.trim().split(" "));
    }
}
