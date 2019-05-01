package currency.converter.service;

import currency.converter.configuration.ApplicationConfiguration;
import currency.converter.model.Currency;
import currency.converter.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class CurrencyService {

    @Autowired
    private ApplicationConfiguration configuration;

    @Autowired
    private CurrencyRepository currencyRepository;

    @PostConstruct
    public void init() {
        //loading all currencies and inserting to cache
        for(Map.Entry<String, Integer> entry : configuration.getCurrencies().entrySet()){
            Currency currency = currencyRepository.getOrInsertCurrency(entry.getKey());
            currency.setDecimalPlaces(entry.getValue());
        }
    }

    public Currency getCurrency(String name){
        return currencyRepository.getCurrency(name);
    }
}
