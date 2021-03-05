package pl.jedro.recognitionApp.strategies;

import pl.jedro.recognitionApp.utils.GenderReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FirstTokenStrategy implements RecognitionStrategy {
    public String determineGender(List<String> names) throws IOException {
        GenderReader maleReader = new GenderReader(new BufferedReader(
                new FileReader("src/main/resources/static/maleTokens.txt")));
        GenderReader femaleReader = new GenderReader(new BufferedReader(
                new FileReader("src/main/resources/static/femaleTokens.txt")));
        if (nameMatchesToken(names, maleReader)) {
            return "MALE";
        } else if (nameMatchesToken(names,femaleReader)) {
            return "FEMALE";
        }
        return "INCONCLUSIVE";
    }

//    private boolean nameMatchesToken(List<String> names, GenderReader reader) throws IOException {
//        return reader.getAllTokens().stream().anyMatch(token -> token.getName().equals(names.get(0)));
//    }
    private boolean nameMatchesToken(List<String> names, GenderReader reader) throws IOException {
       return reader.getStreamTokens().anyMatch(token ->  token.getName().equals(names.get(0)));
    }

}
