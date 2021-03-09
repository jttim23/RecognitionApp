package pl.jedro.recognitionApp.utils;

import pl.jedro.recognitionApp.model.GenderToken;

import java.io.FileNotFoundException;
import java.util.stream.Stream;

public interface GenderTokensReader {
    Stream<GenderToken> getMaleTokensStream() throws FileNotFoundException;

    Stream<GenderToken> getFemaleTokensStream() throws FileNotFoundException;
}
