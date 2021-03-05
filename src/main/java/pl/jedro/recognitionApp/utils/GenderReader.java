package pl.jedro.recognitionApp.utils;

import lombok.AllArgsConstructor;
import pl.jedro.recognitionApp.model.GenderToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class GenderReader {
    private BufferedReader bufferedReader;

    public GenderReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public List<GenderToken> getAllTokens() throws IOException {
        List<GenderToken> list = new ArrayList<>();
        String line = bufferedReader.readLine();
        while (line != null) {
            list.add(new GenderToken(line));
            line = bufferedReader.readLine();
        }
        if (list.isEmpty()) throw new IOException();
        return list;
    }
    public Stream<GenderToken> getStreamTokens() throws IOException {

        return bufferedReader.lines().map(this::mapper);
    }

    private GenderToken mapper(String s) {
        return new GenderToken(s);
    }
}
