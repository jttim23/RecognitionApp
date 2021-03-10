package pl.jedro.recognitionApp.strategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.jedro.recognitionApp.model.Genders;
import pl.jedro.recognitionApp.utils.GenderTokensReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class FirstNameAlgorithm implements RecognitionAlgorithm {
    @Value("${males.path}")
    private String maleTokensPath;

    @Value("${females.path}")
    private String femaleTokensPath;
    @Autowired
    GenderTokensReader readerV2;

    @Override
    public AlgorithmNames getAlgorithmName() {
        return AlgorithmNames.FIRST_NAME_ALGORITHM;
    }

    @Override
    public Genders determineGender(List<String> names) throws IOException {

        if (firstNameMatchesToken(names.get(0), maleTokensPath)) {
            return Genders.MALE;
        } else if (firstNameMatchesToken(names.get(0), femaleTokensPath)) {
            return Genders.FEMALE;
        } else {
            return Genders.INCONCLUSIVE;
        }
    }

    private boolean firstNameMatchesToken(String name, String path) throws IOException {
        BufferedReader tokensReader = new BufferedReader(new FileReader(path));
        String line;
        boolean nameIsFound = false;
        while (((line = tokensReader.readLine()) != null) && (!nameIsFound)) {
            if (name.equals(line.toLowerCase())) {
                nameIsFound = true;
            }
        }
        return nameIsFound;
    }


}
