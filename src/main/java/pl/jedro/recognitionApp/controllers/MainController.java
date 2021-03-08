package pl.jedro.recognitionApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.jedro.recognitionApp.model.Gender;
import pl.jedro.recognitionApp.model.GenderToken;
import pl.jedro.recognitionApp.services.GenderRecognitionService;
import pl.jedro.recognitionApp.strategies.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
public class MainController {
    @Autowired
    private AlgorithmFactory strategyFactory;
    GenderRecognitionService service;

    public MainController(GenderRecognitionService service) {
        this.service = service;
    }

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
                service.setAlgorithm(strategyFactory.findAlgorithm(AlgorithmName.FirstNameAlgorithm));

                break;
            case "2":
                service.setAlgorithm(strategyFactory.findAlgorithm(AlgorithmName.AllNamesAlgorithm));
                break;
        }
    return service.determineGender(name);
    }

}
