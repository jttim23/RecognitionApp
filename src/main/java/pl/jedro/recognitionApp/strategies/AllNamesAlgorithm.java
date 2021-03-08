package pl.jedro.recognitionApp.strategies;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.jedro.recognitionApp.model.Gender;
import pl.jedro.recognitionApp.utils.GenderTokensBufferedReader;
import pl.jedro.recognitionApp.utils.GenderTokensReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
@NoArgsConstructor
public class AllNamesAlgorithm implements RecognitionAlgorithm {
    @Value("${males.path}")
    private String maleTokensPath;
    @Value("${females.path}")
    private String femaleTokensPath;

    @Override
    public AlgorithmName getAlgorithmName() {
        return AlgorithmName.AllNamesAlgorithm;
    }

    @Override
    public Gender determineGender(List<String> names) throws IOException {

        int maleTokens = countTokensMatchingNames(names, new GenderTokensBufferedReader(
                new FileReader(maleTokensPath)));
       int femaleTokens
               = countTokensMatchingNames(names, new GenderTokensBufferedReader(
                new FileReader(femaleTokensPath)));
        if (maleTokens > femaleTokens) {
            return Gender.MALE;
        }
        if (femaleTokens > maleTokens) {
            return Gender.FEMALE;
        } else return Gender.INCONCLUSIVE;
    }

    private int countTokensMatchingNames(List<String> names, GenderTokensReader reader) {
        return (int) reader.getTokensStream().distinct().filter(token -> names.contains(token.getName().toLowerCase())).count();
    }
}
