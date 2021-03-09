package pl.jedro.recognitionApp.strategies;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jedro.recognitionApp.model.GenderToken;
import pl.jedro.recognitionApp.model.Genders;
import pl.jedro.recognitionApp.utils.GenderTokensReader;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Stream;


@NoArgsConstructor
@Component
public class FirstNameAlgorithm implements RecognitionAlgorithm {

    @Autowired
    GenderTokensReader readerV2;

    @Override
    public AlgorithmNames getAlgorithmName() {
        return AlgorithmNames.FIRST_NAME_ALGORITHM;
    }

    @Override
    public Genders determineGender(List<String> names) throws FileNotFoundException {

        if (firstNameMatchesToken(names.get(0), readerV2.getMaleTokensStream())) {
            return Genders.MALE;
        } else if (firstNameMatchesToken(names.get(0), readerV2.getFemaleTokensStream())) {
            return Genders.FEMALE;
        } else {
            return Genders.INCONCLUSIVE;
        }
    }

    private boolean firstNameMatchesToken(String name, Stream<GenderToken> stream) {
        return stream.anyMatch(token -> token.getName().toLowerCase().equals(name));
    }


}
