package pl.jedro.recognitionApp.strategies;

import pl.jedro.recognitionApp.model.GenderToken;
import pl.jedro.recognitionApp.utils.GenderReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FirstTokenStrategy {
    public String getResponse(String name) throws IOException {

        GenderReader maleReader = new GenderReader(new BufferedReader(
                new FileReader("src/main/resources/static/maleTokens.txt")));
        GenderReader femaleReader = new GenderReader(new BufferedReader(
                new FileReader("src/main/resources/static/femaleTokens.txt")));

        if (maleReader.getAllTokens().get(0).equals(new GenderToken(name))){
            System.out.println("MALE");
            return "MALE";
        } else if (femaleReader.getAllTokens().get(0).equals(new GenderToken(name))){
            System.out.println("FEMALE");
            return "FEMALE";}
       return "INCONCLUSIVE";
    }
}
