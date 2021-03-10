package pl.jedro.recognitionApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.jedro.recognitionApp.models.GenderToken;
import pl.jedro.recognitionApp.models.Genders;
import pl.jedro.recognitionApp.services.GenderRecognitionService;
import pl.jedro.recognitionApp.strategies.AlgorithmFactory;
import pl.jedro.recognitionApp.strategies.AlgorithmNames;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainController {
    private GenderRecognitionService service;
    private AlgorithmFactory strategyFactory;

    public MainController(GenderRecognitionService service, AlgorithmFactory strategyFactory) {
        this.service = service;
        this.strategyFactory = strategyFactory;
    }

    @GetMapping("api/lists")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getListOfAllTokens(@RequestParam(value = "gender") Genders gender) throws FileNotFoundException {
        return service.getListOfTokens(gender.toString()).stream().map(GenderToken::getName).collect(Collectors.toList());
    }

    @GetMapping("/api/recognize")
    @ResponseStatus(HttpStatus.OK)
    public Genders getGenderRecognizedByFirstName(@RequestParam("name") String name, @RequestParam(value = "algorithm") AlgorithmNames algorithmName) throws IOException {
        setProperAlgorithmToService(algorithmName);
        return service.determineGender(name);
    }

    private void setProperAlgorithmToService(AlgorithmNames algorithmName) {
        service.setAlgorithm(strategyFactory.findAlgorithm(algorithmName));
    }

}
