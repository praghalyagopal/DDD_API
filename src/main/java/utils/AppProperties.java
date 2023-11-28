package utils;

import org.aeonbits.owner.Config;

@Config.Sources({"file:./src/main/resources/prop/environment.property"})
public interface AppProperties extends Config {
    String endpoint();

    String tokenBasePath();

    String bookingModuleBasePath();

    String userName();

    String password();
}
