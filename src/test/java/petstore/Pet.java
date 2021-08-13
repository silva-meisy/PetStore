// 1 - pacote
package petstore;

// 2 - bibliotecas


import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

// 3 - Classe
public class Pet {
    // 3.1 - Atributos
    String uri = "https://petstore.swagger.io/v2/pet"; // endereço da entidade Pet

    // 3.2 - Métodos e Funções
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // Incluir - Create - Post
    @Test(priority = 1)  // Identifica o método ou função como um teste para o TestNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        // Sintaxe Gherkin
        // Dado - Quando - Então
        // Given - When - Then

        given() // Dado
                .contentType("application/json") // comum em API REST - antigas era "text/xml"
                .log().all()
                .body(jsonBody)
        .when()  // Quando
                .post(uri)
        .then()  // Então
                .log().all()
                .statusCode(200)
                .body("name", is("Coca"))
                .body("status", is("available"))
                .body("category.name", is("xerewr"));
                //.body("tags.name", containsString("sta"))
                //body("tags.name", contains("sta"))

        System.out.println("Teste incluir pet");
    }


    @Test(priority = 2)
    public void consultarPet()
    {
        String petId="32265489";
        String token=

        given()
                .contentType("application/json")
                .log().all()

        .when()
                .get(uri + "/" + petId)

        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Coca"))
                .body("status", is("available"))
                .body("category.name", is("xerewr"))
                .extract()
                .path("category.name");

        System.out.println(" O token é " + token);
        System.out.println("Teste consultar pet");
    }

    @Test(priority = 3)
    public void alterarPet() throws IOException {
        String jsonBody= lerJson("db/pet2.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .put(uri)
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Leona"))
                .body("status", is("sold"))
                .body("category.name", is("pet2"));

        System.out.println("Teste alterar pet");
    }

    @Test(priority = 4)
    public void excluirpet()
    {
        String petId="799999";

        given()
                .contentType("application/json")
                .log().all()

        .when()
                .delete(uri + "/" + petId)

        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(petId));


        System.out.println("Teste excluir pet");

    }

}
