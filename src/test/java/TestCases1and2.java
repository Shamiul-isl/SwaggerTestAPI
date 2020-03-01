
import SwaggerPet.apiTests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCases1and2 {
    @Test
    public void testAdd(){

        apiTests.addPet(15,"sloth");

    }
    @Test (priority = 1)
    public void testDelete(){

        apiTests.deletePet(15);

    }

}
