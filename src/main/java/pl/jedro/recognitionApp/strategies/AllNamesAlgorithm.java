package pl.jedro.recognitionApp.strategies;

import pl.jedro.recognitionApp.model.Gender;
import pl.jedro.recognitionApp.model.GenderToken;
import pl.jedro.recognitionApp.utils.GenderTokensBufferedReader;
import pl.jedro.recognitionApp.utils.GenderTokensReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class AllNamesAlgorithm implements RecognitionAlgorithm {

    private int females = 0;
    private int males = 0;

    @Override
    public Gender determineGender(List<String> names) throws IOException {
        countTokensMatchingNames(names);
        if (males > females) {
            return Gender.MALE;
        }
        if (females > males) {
            return Gender.FEMALE;
        } else return Gender.INCONCLUSIVE;
    }

    private void countTokensMatchingNames(List<String> names) throws IOException {
        GenderTokensReader maleReader = new GenderTokensBufferedReader(
                new FileReader("src/main/resources/static/maleTokens.txt"));
        GenderTokensReader femaleReader = new GenderTokensBufferedReader(
                new FileReader("src/main/resources/static/femaleTokens.txt"));
        males = (int) supplyStreamOfTokens(maleReader).get().filter(token -> names.contains(token.getName())).count();
        females = (int) supplyStreamOfTokens(femaleReader).get().filter(token -> names.contains(token.getName())).count();

    }

    private Supplier<Stream<GenderToken>> supplyStreamOfTokens(GenderTokensReader reader) {
        return reader::getStreamTokens;
    }
}
