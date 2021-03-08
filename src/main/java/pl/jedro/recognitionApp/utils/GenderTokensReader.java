package pl.jedro.recognitionApp.utils;

import pl.jedro.recognitionApp.model.GenderToken;

import java.util.stream.Stream;

public interface GenderTokensReader {
    public Stream<GenderToken> getTokensStream();
}
