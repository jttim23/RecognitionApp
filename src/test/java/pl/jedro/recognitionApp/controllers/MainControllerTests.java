package pl.jedro.recognitionApp.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.FileNotFoundException;
@SpringBootTest
public class MainControllerTests {

@Autowired
MainController mainController;

    @Test
    void responsesWithListOfAllMaleTokens() throws FileNotFoundException {
        Assertions.assertEquals(3, mainController.getListOfMaleTokens().size());
    }
    @Test
    void responsesWithListOfAllFemaleTokens() throws FileNotFoundException {
        Assertions.assertEquals(3, mainController.getListOfFemaleTokens().size());
    }

}
