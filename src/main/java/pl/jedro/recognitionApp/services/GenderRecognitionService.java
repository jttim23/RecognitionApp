package pl.jedro.recognitionApp.services;

import pl.jedro.recognitionApp.model.GenderToken;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public interface GenderRecognitionService {
    String determineGender(String fullName) throws IOException;

    List<GenderToken> getListOfMaleTokens() throws FileNotFoundException;
}
