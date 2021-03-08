package pl.jedro.recognitionApp.strategies;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.jedro.recognitionApp.model.Gender;
import pl.jedro.recognitionApp.model.GenderToken;
import pl.jedro.recognitionApp.utils.GenderTokensBufferedReader;
import pl.jedro.recognitionApp.utils.GenderTokensReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class AllNamesAlgorithm implements RecognitionAlgorithm {
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
        int females = 0;
        int males = 0;
        males = countTokensMatchingNames(names, maleReader);
        females = countTokensMatchingNames(names, femaleReader);
        if (males > females) {
            return Gender.MALE;
        }
        if (females > males) {
            return Gender.FEMALE;
        } else return Gender.INCONCLUSIVE;
    }

    @Override
    public AlgorithmName getAlgorithmName() {
        return AlgorithmName.AllNamesAlgorithm;
    }

    private int countTokensMatchingNames(List<String> names, GenderTokensReader reader) throws IOException {


        return (int) supplyStreamOfTokens(reader).get().filter(token -> names.contains(token.getName())).count();

    }

    private Supplier<Stream<GenderToken>> supplyStreamOfTokens(GenderTokensReader reader) {
        return reader::getStreamTokens;
    }

}
