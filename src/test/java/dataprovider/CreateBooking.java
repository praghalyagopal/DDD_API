package dataprovider;

import POJO.createBookingRequest;
import faker.DataGenerator;
import org.testng.annotations.DataProvider;

public class CreateBooking  extends DataGenerator {

    @DataProvider
    public Object[][] getData() {
        DataGenerator data = new DataGenerator();
        createBookingRequest createRequestBody = new createBookingRequest();
        createRequestBody.setFirstname(data.generateFirstName());
        createRequestBody.setLastname(data.generateLastName());
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
