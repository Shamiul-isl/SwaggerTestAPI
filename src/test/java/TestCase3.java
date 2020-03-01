import org.testng.annotations.Test;
import SwaggerPet.apiTests;

public class TestCase3 {
    @Test
    public void petOrder(){
        apiTests.placePetOrder(3,10,1,"placed");
    }
}
