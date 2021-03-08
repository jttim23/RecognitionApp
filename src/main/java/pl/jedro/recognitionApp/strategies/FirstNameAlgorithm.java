package pl.jedro.recognitionApp.strategies;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.jedro.recognitionApp.model.Gender;
import pl.jedro.recognitionApp.utils.GenderTokensBufferedReader;
import pl.jedro.recognitionApp.utils.GenderTokensReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class FirstNameAlgorithm implements RecognitionAlgorithm {
    @Value("${males.path}")
    private String maleTokensPath;
    @Value("${females.path}")
    private String femaleTokensPath;
    @Override
    public AlgorithmName getAlgorithmName() {
        return AlgorithmName.FirstNameAlgorithm;
    }
    @Override
    public Gender determineGender(List<String> names) throws IOException {
        if (firstNameMatchesToken(names.get(0), new GenderTokensBufferedReader(
                new FileReader(maleTokensPath)))) {
            return Gender.MALE;
        } else if (firstNameMatchesToken(names.get(0), new GenderTokensBufferedReader(
                new FileReader(femaleTokensPath)))) {
            return Gender.FEMALE;
        } else {
            return Gender.INCONCLUSIVE;
        }
    }

    private boolean firstNameMatchesToken(String name, GenderTokensReader reader) {
        return reader.getTokensStream().anyMatch(token -> token.getName().toLowerCase().equals(name));
    }

}
