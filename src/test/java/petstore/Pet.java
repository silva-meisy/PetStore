// 1 - pacote
package petstore;

// 2 - bibliotecas

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// 3 - classe
public class Pet
{
    //3.1 - atributos
    String uri="https://petstore.swagger.io/v2/pet";//endereço da entidade pet


    // 3.2 - métodos e funções
    public String lerJson(String caminhoJson) throws IOException
    {

        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // incluir - create - post
    @Test // identifica o metodo ou funcao como um teste para o TestNG
    public void incluirPet() throws IOException
    {
        String jsonBody = lerJson("db/pet1.json");


        // sintaxe gherkin
        // dado - quando - entao
        // given - when - then

        given() //Dado
                .contentType("application/json")//comum em API REST
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all
                .statusCode(200);

    }

}
