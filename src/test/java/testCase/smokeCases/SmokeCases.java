package testCase.smokeCases;

import POJO.AuthBody_TokenPojo;
import io.restassured.response.Response;
import org.fleetStudio.base.RA_Wrapper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeCases extends RA_Wrapper {

    /**
     * Test to verify that the application is up and running.
     */
    @Test(priority = 1)
    public void verifyApplicationUp() {
        Response response = pingCheck();
        response.then().statusCode(201);
        System.out.println("***********************");
        System.out.println("Verify Application Up Test: Ping Successful");
        System.out.println("***********************");
    }

    /**
     * Test to verify the generation of an authentication token.
     */
    @Test(priority = 2, groups = {"tokenCreation"})
    public void verifyTokenGenerated() {
        AuthBody_TokenPojo authTokenPojo = createAuthTokenPojo();
        String token = authTokenPojo.getToken();
        Assert.assertNotNull(token);
        System.out.println("Verify Token Generated Test: Token - " + token);
    }
}
