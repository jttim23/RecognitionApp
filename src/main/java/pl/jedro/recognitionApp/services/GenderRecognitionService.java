package pl.jedro.recognitionApp.services;

import org.springframework.boot.autoconfigure.web.WebProperties;
import pl.jedro.recognitionApp.model.Gender;
import pl.jedro.recognitionApp.model.GenderToken;
import pl.jedro.recognitionApp.strategies.RecognitionAlgorithm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public interface GenderRecognitionService {
    Gender determineGender(String fullName) throws IOException;
    void setAlgorithm(RecognitionAlgorithm algorithm);

    List<GenderToken> getListOfMaleTokens() throws FileNotFoundException;

    List<GenderToken> getListOfFemaleTokens() throws FileNotFoundException;
}
