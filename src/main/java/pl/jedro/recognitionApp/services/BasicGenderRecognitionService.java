package pl.jedro.recognitionApp.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.jedro.recognitionApp.model.GenderToken;
import pl.jedro.recognitionApp.model.Genders;
import pl.jedro.recognitionApp.strategies.RecognitionAlgorithm;
import pl.jedro.recognitionApp.utils.GenderTokensReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class BasicGenderRecognitionService implements GenderRecognitionService {

    private GenderTokensReader readerV2;
    private RecognitionAlgorithm algorithm;

    public BasicGenderRecognitionService(GenderTokensReader readerV2) {
        this.readerV2 = readerV2;
    }

    @Override
    public void setAlgorithm(RecognitionAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public Genders determineGender(String fullName) throws IOException {
        return algorithm.determineGender(splitNameToArray(fullName));
    }

    @Override
    public List<GenderToken> getListOfTokens(String gender) throws FileNotFoundException {
        switch (gender.toLowerCase()) {
            case "male":
                return getListOfMaleTokens();
            case "female":
                return getListOfFemaleTokens();
            default:
                return new ArrayList<>();
        }
    }

    private List<GenderToken> getListOfMaleTokens() throws FileNotFoundException {
        return readerV2.getMaleTokensStream().collect(Collectors.toCollection(ArrayList::new));
    }


    private List<GenderToken> getListOfFemaleTokens() throws FileNotFoundException {
        return readerV2.getFemaleTokensStream().collect(Collectors.toCollection(ArrayList::new));
    }


    private List<String> splitNameToArray(String fullName) {
        if (fullName.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        return Arrays.asList(fullName.trim().toLowerCase().split(" "));
    }
}
