package SwaggerPet;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;

public class apiTests {
    public static void addPet(int id, String name){
        //create a post request to update the api
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type","application/json");
        JSONObject params = new JSONObject();
        params.put("id",id);
        params.put("name",name);
        params.put("status","available");
        request.body(params.toJSONString());
        Response response = request.post("https://petstore.swagger.io/v2/pet");
        int code = response.getStatusCode();
        System.out.println("Post request returned code "+code);

        //create a get request to validate the post request
        Response r2 = RestAssured.get("https://petstore.swagger.io/v2/pet/"+id);
        int code2 = r2.getStatusCode();
        JsonPath jsonPathEvaluator = r2.jsonPath();
        String apiName = jsonPathEvaluator.get("name").toString();
        System.out.println("Get request returned code "+code2);
        System.out.println("Name received from Api is: "+apiName);
        Assert.assertEquals(code2, 200);
        Assert.assertEquals(apiName,name);
    }

    public static void deletePet(int id){
        //automate a delete request with the given pet id
        RequestSpecification request = RestAssured.given();
        Response response = request.delete("https://petstore.swagger.io/v2/pet/"+id);
        int code = response.getStatusCode();
        System.out.println("Delete request returned code "+code);
        Assert.assertEquals(code, 200);

        //send a get request to make sure the pet doesn't exist
        Response r2 = RestAssured.get("https://petstore.swagger.io/v2/pet/"+id);
        int c2 = r2.getStatusCode();
        System.out.println("Get request returned code "+c2);
        Assert.assertEquals(c2, 404);
    }

    public static void placePetOrder(int id, int petId, int quantity, String status){
        //place a pet order
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type","application/json");
        JSONObject params = new JSONObject();
        params.put("id",id);
        params.put("petId",petId);
        params.put("quantity",quantity);
        params.put("status",status);
        request.body(params.toJSONString());
        Response response = request.post("https://petstore.swagger.io/v2/store/order");
        int code = response.getStatusCode();
        System.out.println("Post request returned code "+code);
        Assert.assertEquals(code,200);

        //verify pet order with a get request
        Response response1 = RestAssured.get("https://petstore.swagger.io/v2/store/order/"+id);
        JsonPath jsonPathEvaluator = response1.jsonPath();
        String orderStatus = jsonPathEvaluator.get("status").toString();
        System.out.println("the order status for this order is "+orderStatus);
        Assert.assertEquals(orderStatus,"placed");
    }
}
