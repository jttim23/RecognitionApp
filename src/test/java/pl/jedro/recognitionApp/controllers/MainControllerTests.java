package pl.jedro.recognitionApp.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class MainControllerTests {

    @Test
    void responsesWithListOfAllMaleTokens() throws FileNotFoundException {
        MainController mainController = new MainController();
        Assertions.assertEquals(3, mainController.getListOfMaleTokens().size());
    }

}
