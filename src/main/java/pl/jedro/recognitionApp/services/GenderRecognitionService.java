package pl.jedro.recognitionApp.services;

import pl.jedro.recognitionApp.model.GenderToken;
import pl.jedro.recognitionApp.model.Genders;
import pl.jedro.recognitionApp.strategies.RecognitionAlgorithm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public interface GenderRecognitionService {
    Genders determineGender(String fullName) throws IOException;

    void setAlgorithm(RecognitionAlgorithm algorithm);

    List<GenderToken> getListOfTokens(String gender) throws FileNotFoundException;
}
