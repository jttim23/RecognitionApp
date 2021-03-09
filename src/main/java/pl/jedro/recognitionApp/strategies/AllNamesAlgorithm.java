package pl.jedro.recognitionApp.strategies;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jedro.recognitionApp.model.GenderToken;
import pl.jedro.recognitionApp.model.Genders;
import pl.jedro.recognitionApp.utils.GenderTokensReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Component
@NoArgsConstructor
public class AllNamesAlgorithm implements RecognitionAlgorithm {

    @Autowired
    private GenderTokensReader readerV2;

    @Override
    public AlgorithmNames getAlgorithmName() {
        return AlgorithmNames.ALL_NAMES_ALGORITHM;
    }

    @Override
    public Genders determineGender(List<String> names) throws IOException {
        int maleTokens = countTokensMatchingNames(names, readerV2.getMaleTokensStream());
        int femaleTokens = countTokensMatchingNames(names, readerV2.getFemaleTokensStream());
        if (maleTokens > femaleTokens) {
            return Genders.MALE;
        }
        if (femaleTokens > maleTokens) {
            return Genders.FEMALE;
        } else return Genders.INCONCLUSIVE;
    }

    private int countTokensMatchingNames(List<String> names, Stream<GenderToken> stream) {
        return (int) stream.distinct().filter(token -> names.contains(token.getName().toLowerCase())).count();
    }
}
