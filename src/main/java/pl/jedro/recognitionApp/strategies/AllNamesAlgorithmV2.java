package pl.jedro.recognitionApp.strategies;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.jedro.recognitionApp.model.Gender;
import pl.jedro.recognitionApp.utils.GenderTokensReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
@NoArgsConstructor
public class AllNamesAlgorithmV2 implements RecognitionAlgorithm {
    @Value("${males.path}")
    private String maleTokensPath;
    @Value("${females.path}")
    private String femaleTokensPath;

    @Override
    public AlgorithmName getAlgorithmName() {
        return AlgorithmName.AllNamesAlgorithmV2;
    }

    @Override
    public Gender determineGender(List<String> names) throws IOException {

        int femalesTokens = countTokensMatchingNames(names, femaleTokensPath);
        int malesTokens = countTokensMatchingNames(names, maleTokensPath);
        if (malesTokens > femalesTokens) {
            return Gender.MALE;
        }
        if (femalesTokens > malesTokens) {
            return Gender.FEMALE;
        } else return Gender.INCONCLUSIVE;
    }

    public int countTokensMatchingNames(List<String> names, String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line;
        int namesToFind = names.size();
        int result = 0;
        while (((line = bufferedReader.readLine()) != null) && (namesToFind > 0)) {
            for (int i = 0; i < names.size(); i++)
                if (line.toLowerCase().equals(names.get(i).toLowerCase())) {
                    namesToFind--;
                    result++;
                }
        }
        return result;
    }

}
