package pl.jedro.recognitionApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jedro.recognitionApp.model.GenderToken;
import pl.jedro.recognitionApp.services.BasicGenderRecognitionService;
import pl.jedro.recognitionApp.services.GenderRecognitionService;
import pl.jedro.recognitionApp.strategies.FirstNameAlgorithm;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class MainController {
    private static final String BASE_URL = "api/males";

    GenderRecognitionService service = new BasicGenderRecognitionService(new FirstNameAlgorithm());

    @GetMapping(BASE_URL)
    public List<GenderToken> getListOfMaleTokens() throws FileNotFoundException {
        return service.getListOfMaleTokens();
    }
}
