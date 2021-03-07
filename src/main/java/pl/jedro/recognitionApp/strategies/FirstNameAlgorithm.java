package pl.jedro.recognitionApp.strategies;

import pl.jedro.recognitionApp.model.Gender;
import pl.jedro.recognitionApp.utils.GenderTokensBufferedReader;
import pl.jedro.recognitionApp.utils.GenderTokensReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FirstNameAlgorithm implements RecognitionAlgorithm {

    public Gender determineGender(List<String> names) throws IOException {
        GenderTokensReader maleReader = new GenderTokensBufferedReader(
                new FileReader("src/main/resources/static/maleTokens.txt"));

        GenderTokensReader femaleReader = new GenderTokensBufferedReader(
                new FileReader("src/main/resources/static/femaleTokens.txt"));
        if (firstNameMatchesToken(names, maleReader)) {
            return Gender.MALE;
        } else if (firstNameMatchesToken(names, femaleReader)) {
            return Gender.FEMALE;
        } else {
            return Gender.INCONCLUSIVE;
        }
    }

    private boolean firstNameMatchesToken(List<String> names, GenderTokensReader reader) {
        return reader.getStreamTokens().anyMatch(token -> token.getName().toLowerCase().equals(names.get(0).toLowerCase()));
    }

}
