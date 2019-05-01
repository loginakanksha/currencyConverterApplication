package currency.converter.service;

import currency.converter.configuration.ApplicationConfiguration;
import currency.converter.exception.CurrencyConverterException;
import currency.converter.model.Currency;
import currency.converter.repository.ConversionRateGraphImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class ConversionRateService {

    private static final Logger LOGGER = Logger.getLogger(ConversionRateService.class);

    @Autowired
    private ApplicationConfiguration configuration;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ConversionRateGraphImpl conversionRateGraphImpl;

    @PostConstruct
    public void init() {

        //loading all conversion rates
        for(Map.Entry<String, Double> entry : configuration.getConversionRates().entrySet()){
            String from = entry.getKey().substring(0,3);
            String to = entry.getKey().substring(3);

            Currency fromCurr = currencyService.getCurrency(from);
            if(fromCurr == null){
                LOGGER.error("Invalid From currency : "+from);
                continue;
            }

            Currency toCurr = currencyService.getCurrency(to);
            if(toCurr == null){
                LOGGER.error("Invalid To currency : "+to);
                continue;
            }

            conversionRateGraphImpl.addConversionRate(fromCurr, toCurr, entry.getValue());
        }

        //pre-calculating all possible combination
        conversionRateGraphImpl.normalizeGraph();
    }

    public double getConversionRate(Currency from, Currency to) throws CurrencyConverterException{
        return conversionRateGraphImpl.getConversionRate(from, to);
    }
}
