package pl.pwr.orders;

import org.apache.commons.configuration2.ex.ConfigurationException;
import tools.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws ConfigurationException, IOException {
        AppSettings settings = new AppSettings();
        OrderFileLoader loader = new OrderFileLoader(settings.getOrdersDirectory());
        JsonMapper mapper = JsonMapper.builder().build();

        for (Path file : loader.loadJsonFiles()) {
            OrderRequest order = mapper.readValue(file.toFile(), OrderRequest.class);
            String number = OrderNumberGenerator.generate(order);
            System.out.println(file.getFileName() + " -> numer zamowienia: " + number);
        }
    }
}