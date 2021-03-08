package pl.jedro.recognitionApp.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.jedro.recognitionApp.services.BasicGenderRecognitionService;

import java.io.FileNotFoundException;

public class MainControllerTests {


    @Test
    void responsesWithListOfAllMaleTokens() throws FileNotFoundException {
        MainController mainController = new MainController(new BasicGenderRecognitionService());
        Assertions.assertEquals(3, mainController.getListOfMaleTokens().size());
    }
    @Test
    void responsesWithListOfAllFemaleTokens() throws FileNotFoundException {
        MainController mainController = new MainController(new BasicGenderRecognitionService());
        Assertions.assertEquals(3, mainController.getListOfFemaleTokens().size());
    }

}
