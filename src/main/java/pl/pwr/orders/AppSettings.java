
//klasa do wczytywania ustawien
package pl.pwr.orders;

//biblioteka Apache Commons Configuration

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class AppSettings {

    private static final String SETTINGS_FILE ="appsettings.properties";

    private final Configuration config;     //klucz->wartosc

    public AppSettings() throws ConfigurationException {
        this.config = new Configurations().properties(SETTINGS_FILE);

        //properties() wczytuje plik appsettings.properties
        //Configurations() zwraca obiekt Configuration z zawartością

    }
    public String getOrdersDirectory() {
        return config.getString("orders.directory");
    }
}
