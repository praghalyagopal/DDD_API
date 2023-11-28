package faker;

import com.github.javafaker.Faker;
import org.fleetStudio.base.RA_Wrapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DataGenerator  {
    private final Faker faker;
    private final SimpleDateFormat dateFormat;
    private String checkinDate;
    private static final Random random = new Random();

    public DataGenerator() {
        this.faker = new Faker();
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Date format for check-in and check-out dates
    }


    public String generateFirstName() {
        return faker.name().firstName();
    }

    public String generateLastName() {
        return faker.name().lastName();
    }


    public String generateTotalPrice() {
        return String.valueOf(faker.number().randomDouble(2, 50, 500));
    }


    public String generateCheckinDate() {
        Date date = faker.date().between(new Date(), faker.date().future(30, TimeUnit.DAYS));
        checkinDate = dateFormat.format(date);
        return checkinDate;
    }

    public String generateCheckoutDate() {
        try {
            Date checkin = dateFormat.parse(checkinDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(checkin);
            calendar.add(Calendar.DAY_OF_MONTH, 5); // Add 5 days to the check-in date
            Date checkout = calendar.getTime();
            return dateFormat.format(checkout);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid Date"; // Handle the exception as needed
        }

    }

    public String generateExtraNeeds() {
        return faker.lorem().fixedString(5);
    }

    public String generateDepositPaid(){
        String[] paid = {"true", "false"};
        return paid[random.nextInt(paid.length)];
    }

}
