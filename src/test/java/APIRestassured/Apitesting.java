package APIRestassured;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;



import static io.restassured.RestAssured.*;

public class Apitesting {

    @Test
    public void createToken(){

        RequestSpecification req = new RequestSpecBuilder().build();
        req.baseUri("https://restful-booker.herokuapp.com").header("Content-Type", "application/json");
        ResponseSpecification res = new ResponseSpecBuilder().build();
        res.statusCode(200);

        Response response =given().spec(req).body("{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}").when().post("/auth")
                .then().assertThat().spec(res).extract().response();

        String token = response.jsonPath().getString("token");
        System.out.println(token);
    }
}
