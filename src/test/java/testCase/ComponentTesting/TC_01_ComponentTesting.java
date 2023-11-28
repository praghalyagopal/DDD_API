package testCase.ComponentTesting;

import POJO.createBookingRequest;
import dataprovider.CreateBooking;
import faker.DataGenerator;
import io.restassured.response.Response;
import org.fleetStudio.base.RA_Wrapper;
import org.testng.annotations.Test;

public class TC_01_ComponentTesting extends RA_Wrapper {
    DataGenerator data = new DataGenerator();
    public String bookingid;

    /**
     * Test to verify the end-to-end flow of creating a booking.
     *
     * @param requestBody The valid booking request body.
     */
    @Test(priority = 1, dependsOnMethods = {"testCase.smokeCases.SmokeCases.verifyApplicationUp"},
            dataProvider = "getData", dataProviderClass = CreateBooking.class)
    public void verifyCreateBookingFlow(createBookingRequest requestBody) {
        Response booking = createBooking(requestBody);
        booking.then().statusCode(200);
        bookingid = booking.getBody().jsonPath().get("bookingid").toString();
        System.out.println("Create Booking Flow Test: Booking ID created- " + bookingid);
    }

    /**
     * Test to verify the retrieval of a booking using the created booking ID.
     */
    @Test(dependsOnMethods = {"verifyCreateBookingFlow"})
    public void verifyGetBookingId() {
        Response booking = getBooking(bookingid);
        booking.prettyPrint();
        booking.then().statusCode(200);
        System.out.println("Get Booking ID Test: Response Code - " + booking.statusCode());
    }

    /**
     * Test to verify the update of a booking with new information.
     *
     * @throws Exception If an error occurs during the update.
     */
    @Test(dependsOnMethods = {"verifyGetBookingId"})
    public void verifyUpdateBooking() throws Exception {
        createBookingRequest bookingPojo = getBookingPojo(bookingid);
        bookingPojo.setLastname(data.generateLastName());
        Response response = partialUpdateBooking(bookingPojo, bookingid);
        response.prettyPrint();
        System.out.println("Update Booking Test: Response Code - " + response.statusCode());
    }

    /**
     * Test to verify the delete api  of a booking using the created booking ID.
     */
    @Test(dependsOnMethods = {"verifyUpdateBooking"})
    public void verifyDeleteBookingId() {
        Response booking = deleteBooking(bookingid);
        booking.prettyPrint();
        booking.then().statusCode(201);
        System.out.println("Delete Booking ID Test: Response Code - " + booking.statusCode());
    }
}
