package pl.jedro.recognitionApp.services;
import java.io.IOException;


public interface GenderRecognitionService {
        String determineGender(String fullName) throws IOException;
}
