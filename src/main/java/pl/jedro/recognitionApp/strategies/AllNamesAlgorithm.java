package pl.jedro.recognitionApp.strategies;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.jedro.recognitionApp.model.Genders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AllNamesAlgorithm implements RecognitionAlgorithm {
    @Value("${males.path}")
    private String maleTokensPath;

    @Value("${females.path}")
    private String femaleTokensPath;

    @Override
    public AlgorithmNames getAlgorithmName() {
        return AlgorithmNames.ALL_NAMES_ALGORITHM;
    }

    @Override
    public Genders determineGender(List<String> names) throws IOException {
        int maleTokens = countTokensMatchingNames(names, maleTokensPath);
        int femaleTokens = countTokensMatchingNames(names, femaleTokensPath);
        if (maleTokens > femaleTokens) {
            return Genders.MALE;
        }
        if (femaleTokens > maleTokens) {
            return Genders.FEMALE;
        } else return Genders.INCONCLUSIVE;
    }

    private int countTokensMatchingNames(List<String> names, String path) throws IOException {
        Set<String> nameSet = new HashSet<>(names);
        BufferedReader tokensReader = new BufferedReader(new FileReader(path));
        String line;
        int namesToFind = names.size();
        int result = 0;
        while (((line = tokensReader.readLine()) != null) && (namesToFind > 0)) {
            if (nameSet.contains(line.toLowerCase())) {
                namesToFind--;
                result++;
            }
        }
        return result;
    }


}

