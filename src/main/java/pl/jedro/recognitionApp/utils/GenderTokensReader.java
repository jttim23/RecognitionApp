package pl.jedro.recognitionApp.utils;

import pl.jedro.recognitionApp.model.GenderToken;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.stream.Stream;


public class GenderTokensReader extends BufferedReader {

    public GenderTokensReader(Reader in) {
        super(in);
    }

    public Stream<GenderToken> getStreamTokens() {
        return super.lines().map(this::mapper);
    }

    private GenderToken mapper(String s) {
        return new GenderToken(s);
    }
}
