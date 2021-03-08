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
    public Gender determineGender(List<String> names) throws IOException {

        GenderTokensReader maleReader = new GenderTokensBufferedReader(

                new FileReader(maleTokensPath));

        GenderTokensReader femaleReader = new GenderTokensBufferedReader(
                new FileReader(femaleTokensPath));
        if (firstNameMatchesToken(names, maleReader)) {
            return Gender.MALE;

        } else if (firstNameMatchesToken(names, femaleReader)) {
            return Gender.FEMALE;
        } else {
            return Gender.INCONCLUSIVE;
        }
    }

    @Override
    public AlgorithmName getAlgorithmName() {
        return AlgorithmName.FirstNameAlgorithm;
    }

    private boolean firstNameMatchesToken(List<String> names, GenderTokensReader reader) {
        return reader.getStreamTokens().anyMatch(token -> token.getName().toLowerCase().equals(names.get(0).toLowerCase()));
    }

}
