package testCase.NegativeCases;

import POJO.createBookingRequest;
import dataprovider.CreateBookingInvalidPOJO;
import io.restassured.response.Response;
import org.fleetStudio.base.RA_Wrapper;
import org.testng.annotations.Test;

public class TC_01_CreatingBookingThruInvalidInput extends RA_Wrapper {

    /**
     * Test to verify the creation of booking with empty request body.
     *
     * @param requestBody The invalid booking request body.
     */
    @Test(groups = {"negative"},
            dataProvider = "getData", dataProviderClass = CreateBookingInvalidPOJO.class)
    public void verifyWithEmptyReqBody(createBookingRequest requestBody) {
        Response booking = createBooking(requestBody);
        booking.then().statusCode(500);
        System.out.println("Verify With Empty Request Body Test: Response Code - " + booking.statusCode());
    }

    /**
     * Test to verify the response code for a non-existent booking ID.
     */
    @Test(groups = {"negative"})
    public void verifyGetBookingId() {
        Response nonExistentBookingResponse = getBooking("nonExistentBookingId");
        nonExistentBookingResponse.then().statusCode(404);
        System.out.println("Verify Get Booking ID Test: Response Code - " + nonExistentBookingResponse.statusCode());
    }
}
