package utils;

import org.aeonbits.owner.ConfigFactory;
import utils.AppProperties;

public class ApplicationProLoader {

  public static AppProperties prop = null;
    public static AppProperties loadProps() {
        if (prop == null) {
            prop = ConfigFactory.create(AppProperties.class);
        }
        return prop;
    }




}
