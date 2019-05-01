package currency.converter.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties("myapp")
public class ApplicationConfiguration {

    private Map<String, Integer> currencies;
    private Map<String, Double> conversionRates;

    public Map<String, Integer> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<String, Integer> currencies) {
        this.currencies = currencies;
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    public void setConversionRates(Map<String, Double> conversionRates) {
        this.conversionRates = conversionRates;
    }
}
