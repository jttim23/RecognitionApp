package pl.jedro.recognitionApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.jedro.recognitionApp.model.Gender;
import pl.jedro.recognitionApp.model.GenderToken;
import pl.jedro.recognitionApp.services.BasicGenderRecognitionService;
import pl.jedro.recognitionApp.services.GenderRecognitionService;
import pl.jedro.recognitionApp.strategies.AllNamesAlgorithm;
import pl.jedro.recognitionApp.strategies.FirstNameAlgorithm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
public class MainController {

    GenderRecognitionService service = new BasicGenderRecognitionService();

    @GetMapping("api/lists/males")
    public List<GenderToken> getListOfMaleTokens() throws FileNotFoundException {
        return service.getListOfMaleTokens();
    }
    @GetMapping("api/lists/females")
    public List<GenderToken> getListOfFemaleTokens() throws FileNotFoundException {
        return service.getListOfFemaleTokens();
    }
    @GetMapping("/api/recognize")
    public Gender getGenderRecognizedByFirstName(@RequestParam("name") String name, @RequestParam("algorithm") String algorithmNumber) throws IOException {
        switch (algorithmNumber){
            case "1":
                service.setAlgorithm(new FirstNameAlgorithm());
                break;
            case "2":
                service.setAlgorithm(new AllNamesAlgorithm());
                break;
        }
    return service.determineGender(name);
    }

}
