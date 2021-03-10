package pl.jedro.recognitionApp.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.jedro.recognitionApp.RecognitionAppApplication;
import pl.jedro.recognitionApp.model.Genders;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItem;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RecognitionAppApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainControllerIT {
    @Value("${local.server.port}")
    private int ports;

    @BeforeAll
    public void setUp() {
        port = ports;
        baseURI = "http://localhost" + "/api/recognize";
    }

    @Test
    void respondsWithMaleifNameIsMale() {
        given().queryParam("name", "Jan Maria Rokita")
                .queryParam("algorithm", "FIRST_NAME_ALGORITHM").when()
                .get("http://localhost/api/recognize").then().statusCode(200).assertThat().body(Matchers.containsString(Genders.MALE.toString()));
    }

    @Test
    void respondsWithFemaleifNameIsFemale() {
        given().queryParam("name", "Maria Jan Rokita")
                .queryParam("algorithm", "FIRST_NAME_ALGORITHM").when()
                .get("http://localhost/api/recognize").then().statusCode(200).assertThat().body(Matchers.containsString(Genders.FEMALE.toString()));
    }

    @Test
    void respondsInconclusive() {
        given().queryParam("name", "Rokita Jan Maria")
                .queryParam("algorithm", "FIRST_NAME_ALGORITHM").when()
                .get("http://localhost/api/recognize").then().statusCode(200).assertThat().body(Matchers.containsString(Genders.INCONCLUSIVE.toString()));
    }

    @Test
    void respondsInconclusiveAllNames() {
        given().queryParam("name", "Rokita Jan Maria")
                .queryParam("algorithm", "ALL_NAMES_ALGORITHM").when()
                .get("http://localhost/api/recognize").then().statusCode(200).assertThat().body(Matchers.containsString(Genders.INCONCLUSIVE.toString()));
    }

    @Test
    void respondsWithMaleifNameIsMaleAllNames() {
        given().queryParam("name", "Jan Zbigniew Rokita")
                .queryParam("algorithm", "ALL_NAMES_ALGORITHM").when()
                .get("http://localhost/api/recognize").then().statusCode(200).assertThat().body(Matchers.containsString(Genders.MALE.toString()));
    }

    @Test
    void respondsWithFemaleifNameIsFemaleAllNames() {
        given().queryParam("name", "Maria Anna Rokita")
                .queryParam("algorithm", "ALL_NAMES_ALGORITHM").when()
                .get("http://localhost/api/recognize").then().statusCode(200).assertThat().body(Matchers.containsString(Genders.FEMALE.toString()));
    }

    @Test
    void respondsWithListOfFemaleTokens() {
        given().queryParam("gender", "FEMALE").when()
                .get("http://localhost/api/lists").then().statusCode(200).assertThat().body("", hasItem("Anna"));
    }

    @Test
    void respondsWithListOfMaleTokens() {
        given().queryParam("gender", "MALE").when()
                .get("http://localhost/api/lists").then().statusCode(200).assertThat().body("", hasItem("Jan"));
    }

}