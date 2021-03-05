package pl.jedro.recognitionApp.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenderReader {

    public GenderToken getGenderToken(FileReader fileReader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        if (line == null) throw new IOException();
        return new GenderToken(line);
    }

    public List<GenderToken> getAllTokens(FileReader fileReader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<GenderToken> list = new ArrayList<>();
        String line = bufferedReader.readLine();
        while (line != null) {
            list.add(new GenderToken(line));
            line = bufferedReader.readLine();
        }
        return list;
    }
}
