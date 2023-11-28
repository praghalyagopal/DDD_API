package dataprovider;

import POJO.createBookingRequest;
import faker.DataGenerator;
import org.testng.annotations.DataProvider;

public class CreateBookingInvalidPOJO extends DataGenerator {

    /*
        This class returns a JSON object which misses the basic mandatory fiels like first Name lastName
     */
    @DataProvider
    public Object[][] getData() {
        DataGenerator data = new DataGenerator();
        createBookingRequest createRequestBody = new createBookingRequest();
        createRequestBody.setTotalprice(data.generateTotalPrice());
        createRequestBody.setDepositpaid(data.generateDepositPaid());
        createBookingRequest.BookingDates checkingDates = new createBookingRequest.BookingDates();
        checkingDates.setCheckin(data.generateCheckinDate());
        checkingDates.setCheckout(data.generateCheckoutDate());
        createRequestBody.setBookingdates(checkingDates);
        createRequestBody.setAdditionalneeds(data.generateExtraNeeds());
        return new Object[][]{{createRequestBody}};
    }
}
