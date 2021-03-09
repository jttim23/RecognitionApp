package pl.jedro.recognitionApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.jedro.recognitionApp.exceptions.BadParametersException;
import pl.jedro.recognitionApp.model.GenderToken;
import pl.jedro.recognitionApp.model.Genders;
import pl.jedro.recognitionApp.services.GenderRecognitionService;
import pl.jedro.recognitionApp.strategies.AlgorithmFactory;
import pl.jedro.recognitionApp.strategies.AlgorithmNames;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainController {
    GenderRecognitionService service;
    @Autowired
    private AlgorithmFactory strategyFactory;

    public MainController(GenderRecognitionService service) {
        this.service = service;
    }

    @GetMapping("api/lists")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getListOfAllTokens(@RequestParam(value = "gender", required = false) Genders gender) throws FileNotFoundException {
        if (gender != null) {
            System.out.println(gender.toString());
            return service.getListOfTokens(gender.toString()).stream().map(GenderToken::getName).collect(Collectors.toList());
        } else throw new BadParametersException();
    }

    @GetMapping("/api/recognize")
    @ResponseStatus(HttpStatus.OK)
    public Genders getGenderRecognizedByFirstName(@RequestParam("name") String name, @RequestParam(value = "algorithm", required = false) AlgorithmNames algorithmName) throws IOException {

        if (algorithmName != null) {
            setProperAlgorithmToService(algorithmName);
            return service.determineGender(name);
        } else throw new BadParametersException();
    }

    private void setProperAlgorithmToService(AlgorithmNames algorithmName) {
        service.setAlgorithm(strategyFactory.findAlgorithm(algorithmName));
    }

}
