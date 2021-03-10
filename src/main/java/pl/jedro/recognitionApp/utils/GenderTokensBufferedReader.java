package pl.jedro.recognitionApp.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import pl.jedro.recognitionApp.exceptions.PathNotSpecifiedException;
import pl.jedro.recognitionApp.model.GenderToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Stream;

@Component
public class GenderTokensBufferedReader implements GenderTokensReader {
    @Value("${males.path}")
    private String maleTokensPath;

    @Value("${females.path}")
    private String femaleTokensPath;


    @Override
    public Stream<GenderToken> getMaleTokensStream() throws FileNotFoundException {
        return getGenderTokenStream(maleTokensPath);
    }

    @Override
    public Stream<GenderToken> getFemaleTokensStream() throws FileNotFoundException {
        return getGenderTokenStream(femaleTokensPath);
    }

    private Stream<GenderToken> getGenderTokenStream(String tokensPath) throws FileNotFoundException {
        if (maleTokensPath == null) {
            throw new PathNotSpecifiedException();
        }
        BufferedReader reader = new BufferedReader(new FileReader(tokensPath));
        return reader.lines().map(this::mapper);
    }

    private GenderToken mapper(String s) {
        return new GenderToken(s);
    }
}
