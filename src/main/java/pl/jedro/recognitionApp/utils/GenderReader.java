package pl.jedro.recognitionApp.utils;

import org.apache.catalina.webresources.FileResource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GenderReader {
    public GenderToken getOneToken() throws IOException {
        return getGenderToken();
    }

    private GenderToken getGenderToken() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader( "src/main/resources/static/femaleTokens.txt"));
        String line = bufferedReader.readLine();
        if (line == null) throw new IOException();
        return new GenderToken(line);
    }
}
